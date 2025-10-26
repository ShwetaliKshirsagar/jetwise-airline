package com.jetwise_airline.flight_service.service;

import com.jetwise_airline.flight_service.dto.FlightRequestDTO;
import com.jetwise_airline.flight_service.dto.FlightResponseDTO;
import com.jetwise_airline.flight_service.entity.FlightEntity;
import com.jetwise_airline.flight_service.exceptions.FlightAlreadyExists;
import com.jetwise_airline.flight_service.exceptions.FlightNotFoundException;
import com.jetwise_airline.flight_service.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {
    @Mock
    private FlightRepository repository;
    @Mock
    private FlightRequestDTO flightRequestDTO;
    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    void addFlightTest() {
        FlightRequestDTO flightRequestDTO = new FlightRequestDTO();
        flightRequestDTO.setFlightNumber("A1345");
        flightRequestDTO.setSource("Delhi");
        flightRequestDTO.setDestination("Mumbai");
        flightRequestDTO.setDepartureTime(LocalDateTime.of(2025, 10, 27, 10, 30));
        flightRequestDTO.setArrivalTime(LocalDateTime.of(2025, 10, 27, 12, 45));
        flightRequestDTO.setCapacity(50);
        flightRequestDTO.setPrice(4999.99);

        FlightEntity flightEntity = FlightRequestDTO.toEntity(flightRequestDTO);

        // Mock repository behavior
        Mockito.when(repository.findByFlightNumber("A1345")).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(FlightEntity.class))).thenReturn(flightEntity);

        // Act
        flightService.addFlight(flightRequestDTO);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).findByFlightNumber("A1345");
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(FlightEntity.class));
    }

    @Test
    void addFlightThrowsFlightNotFoundExceptionTest() {
        FlightRequestDTO flightRequestDTO = new FlightRequestDTO();
        flightRequestDTO.setFlightNumber("A1345");
        FlightEntity existing = new FlightEntity();
        existing.setFlightNumber("A1345");
        Mockito.when(repository.findByFlightNumber(flightRequestDTO.getFlightNumber())).thenReturn(Optional.of(existing));
        FlightAlreadyExists exception = assertThrows(FlightAlreadyExists.class,
                () -> flightService.addFlight(flightRequestDTO));
        Assertions.assertEquals("FLIGHT.ALREADY.EXISTS", exception.getMessage());
    }

    @Test
    void updateFlightTest() {

        FlightRequestDTO flightRequestDTO = new FlightRequestDTO();
        flightRequestDTO.setFlightNumber("A1345");
        flightRequestDTO.setSource("Delhi");
        flightRequestDTO.setDestination("Mumbai");
        flightRequestDTO.setDepartureTime(LocalDateTime.of(2025, 10, 27, 10, 30));
        flightRequestDTO.setArrivalTime(LocalDateTime.of(2025, 10, 27, 12, 45));
        flightRequestDTO.setCapacity(50);
        flightRequestDTO.setPrice(4999.99);

        FlightEntity existing = new FlightEntity();
        existing.setFlightNumber("A1345");
        existing.setSource("Mumbai");
        existing.setDestination("Delhi");
        existing.setDepartureTime(LocalDateTime.of(2025, 10, 28, 10, 30));
        existing.setArrivalTime(LocalDateTime.of(2025, 10, 28, 12, 45));
        existing.setCapacity(45);
        existing.setPrice(5999.99);

        FlightEntity flightEntityExpected = FlightRequestDTO.toEntity(flightRequestDTO);

        Mockito.when(repository.findByFlightNumber(flightRequestDTO.getFlightNumber())).thenReturn(Optional.of(existing));
        Mockito.when(repository.save(Mockito.any(FlightEntity.class))).thenReturn(existing);

        FlightResponseDTO flightResponseDTOLatest = flightService.updateFlight(flightRequestDTO);

        Assertions.assertEquals(flightRequestDTO.getArrivalTime(), flightResponseDTOLatest.getArrivalTime());

    }

    @Test
    void updateFlightThrowsFlightNotFoundExceptionTest() {

        FlightRequestDTO flightRequestDTO = new FlightRequestDTO();
        flightRequestDTO.setFlightNumber("A1345");
        flightRequestDTO.setSource("Delhi");
        flightRequestDTO.setDestination("Mumbai");
        flightRequestDTO.setDepartureTime(LocalDateTime.of(2025, 10, 27, 10, 30));
        flightRequestDTO.setArrivalTime(LocalDateTime.of(2025, 10, 26, 12, 45));
        flightRequestDTO.setCapacity(50);
        flightRequestDTO.setPrice(4999.99);

        FlightEntity existing = new FlightEntity();
        existing.setFlightNumber("A1345");
        existing.setSource("Mumbai");
        existing.setDestination("Delhi");
        existing.setDepartureTime(LocalDateTime.of(2025, 10, 28, 10, 30));
        existing.setArrivalTime(LocalDateTime.of(2025, 10, 28, 12, 45));
        existing.setCapacity(45);
        existing.setPrice(5999.99);

        Mockito.when(repository.findByFlightNumber(flightRequestDTO.getFlightNumber())).thenReturn(Optional.empty());

        FlightNotFoundException notFoundException = assertThrows(FlightNotFoundException.class,
                () -> flightService.updateFlight(flightRequestDTO));

        assertEquals("FLIGHT.NOT.FOUND", notFoundException.getMessage());
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());


    }

    @Test
    void updateFlightThrowsRuntimeExceptionTest() {
        FlightRequestDTO flightRequestDTO = new FlightRequestDTO();
        flightRequestDTO.setFlightNumber("A1345");
        flightRequestDTO.setSource("Delhi");
        flightRequestDTO.setDestination("Mumbai");
        flightRequestDTO.setDepartureTime(LocalDateTime.of(2025, 10, 27, 10, 30));
        flightRequestDTO.setArrivalTime(LocalDateTime.of(2025, 10, 26, 12, 45));
        flightRequestDTO.setCapacity(50);
        flightRequestDTO.setPrice(4999.99);

        FlightEntity existing = new FlightEntity();
        existing.setFlightNumber("A1345");
        existing.setSource("Mumbai");
        existing.setDestination("Delhi");
        existing.setDepartureTime(LocalDateTime.of(2025, 10, 28, 10, 30));
        existing.setArrivalTime(LocalDateTime.of(2025, 10, 28, 12, 45));
        existing.setCapacity(45);
        existing.setPrice(5999.99);

        Mockito.when(repository.findByFlightNumber(flightRequestDTO.getFlightNumber())).thenReturn(Optional.of(existing));


        RuntimeException arrivalTimeBeforeException = assertThrows(RuntimeException.class,
                () -> flightService.updateFlight(flightRequestDTO));

        assertEquals("Arrival Time cannot be before departure time", arrivalTimeBeforeException.getMessage());
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());


    }

 /*   @Test
    void deleteFlightTest() {
    }

    @Test
    void searchFlights() {
    }

    @Test
    void getFlightById() {
    }
*/
}