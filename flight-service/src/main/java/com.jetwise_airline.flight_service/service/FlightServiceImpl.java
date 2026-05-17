package com.jetwise_airline.flight_service.service;

import com.jetwise_airline.flight_service.dto.FlightRequestDTO;
import com.jetwise_airline.flight_service.dto.FlightResponseDTO;
import com.jetwise_airline.flight_service.entity.FlightEntity;
import com.jetwise_airline.flight_service.exceptions.FlightAlreadyExists;
import com.jetwise_airline.flight_service.exceptions.FlightNotFoundException;
import com.jetwise_airline.flight_service.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.management.ThreadInfo;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addFlight(FlightRequestDTO flightRequest) throws FlightAlreadyExists {
      if(flightRepository.findByFlightNumber(flightRequest.getFlightNumber()).isPresent()){
          throw new FlightAlreadyExists("FLIGHT.ALREADY.EXISTS");
      }else{
         flightRepository.save(modelMapper.map(flightRequest,FlightEntity.class));
      }
    }

    @Override
    @CachePut(value = "getFlightCache",key = "#flightRequest.flightNumber")
    public FlightResponseDTO updateFlight(FlightRequestDTO flightRequest) throws FlightNotFoundException {

        Optional<FlightEntity> existingFlight = flightRepository.findByFlightNumber(flightRequest.getFlightNumber());
        if(existingFlight.isEmpty()){
            throw new FlightNotFoundException("FLIGHT.NOT.FOUND");
        }

        //Update route only if not null and Changed
        if(flightRequest.getSource()!= null && !flightRequest.getSource().equals(existingFlight.get().getSource())){
            existingFlight.get().setSource(flightRequest.getSource());
        }
        if(flightRequest.getDestination()!= null && !flightRequest.getDestination().equals(existingFlight.get().getDestination())){
            existingFlight.get().setDestination(flightRequest.getDestination());
        }

        //Update timings only if not null and Changed
        if(flightRequest.getDepartureTime()!= null && !flightRequest.getDepartureTime().equals(existingFlight.get().getDepartureTime())){
            if(flightRequest.getArrivalTime().isBefore(flightRequest.getDepartureTime())){
                throw new RuntimeException("Arrival Time cannot be before departure time");      }

            existingFlight.get().setDepartureTime(flightRequest.getDepartureTime());
        }
        if(flightRequest.getArrivalTime()!= null && !flightRequest.getArrivalTime().equals(existingFlight.get().getArrivalTime())){
            if(flightRequest.getArrivalTime().isBefore(flightRequest.getDepartureTime())){
                throw new RuntimeException("Arrival Time cannot be before departure time");      }
            existingFlight.get().setArrivalTime(flightRequest.getArrivalTime());
        }

        //Seats handling
        if(flightRequest.getCapacity()<existingFlight.get().getCapacity()) {
            throw new RuntimeException("Cannot reduce seat capacity after publishing flight.");
        }else{
            existingFlight.get().setCapacity(flightRequest.getCapacity());
        }
       flightRepository.save(existingFlight.get());

        return modelMapper.map(existingFlight.get(),FlightResponseDTO.class);

    }

    @Override
    @CacheEvict(value = "getFlightCache",key = "#flightNumber")
    public void deleteFlight(String flightNumber) throws FlightNotFoundException {
        FlightEntity flightEntity = flightRepository.findByFlightNumber(flightNumber).
                orElseThrow(() -> new FlightNotFoundException("FLIGHT.NOT.FOUND"));
        flightRepository.deleteById(flightEntity.getId());
    }

    @Override
    public List<FlightResponseDTO> searchFlights(String source, String destination) throws FlightNotFoundException {
        List<FlightEntity> availableFlights = flightRepository.findBySourceAndDestination(source, destination)
                .orElseThrow(() -> new FlightNotFoundException("FLIGHT.NOT.FOUND"));

        return availableFlights.stream()
                .map(flight->modelMapper.map(flight,FlightResponseDTO.class))
                .toList();
    }

    @Override
    @Cacheable(value="getFlightCache" ,key = "#flightId")
    public FlightResponseDTO getFlightById(Long flightId) throws FlightNotFoundException {
        //Added thread sleep for cache testing.
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
        }
        FlightEntity flightEntity = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("FLIGHT.NOT.FOUND"));
        return modelMapper.map(flightEntity, FlightResponseDTO.class);

    }

}
