package com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.businesslayer;

import com.champlain.ateliermecaniquews.customeraccountsmanagementsubdomain.datalayer.CustomerAccount;
import com.champlain.ateliermecaniquews.customeraccountsmanagementsubdomain.datalayer.CustomerAccountRepository;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.datalayer.TransmissionType;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.datalayer.Vehicle;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.datalayer.VehicleIdentifier;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.datalayer.VehicleRepository;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.datamapperlayer.VehicleRequestMapper;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.datamapperlayer.VehicleResponseMapper;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.presentationlayer.VehicleRequestModel;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.presentationlayer.VehicleResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    private VehicleResponseMapper vehicleResponseMapper;

    private VehicleRequestMapper vehicleRequestMapper;

    private CustomerAccountRepository customerAccountRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleResponseMapper vehicleResponseMapper, VehicleRequestMapper vehicleRequestMapper, CustomerAccountRepository customerAccountRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleResponseMapper = vehicleResponseMapper;
        this.vehicleRequestMapper = vehicleRequestMapper;
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public List<VehicleResponseModel> getAllVehiclesForCustomer(String customerId) {
        List<Vehicle> vehicles = vehicleRepository.findAllByCustomerId(customerId);
        log.info("Fetching vehicles for customer ID: {}", customerId);


        // Check if the vehicles list is empty or null
        if (vehicles == null) {
            log.warn("No vehicles found for customer ID: {} (vehicles is null)", customerId);
            return Collections.emptyList();
        } else if (vehicles.isEmpty()) {
            log.warn("No vehicles found for customer ID: {} (vehicles list is empty)", customerId);
            return Collections.emptyList();
        } else {
            // Log the number of vehicles found
            log.info("Number of vehicles found for customer ID {}: {}", customerId, vehicles.size());
        }

        List<VehicleResponseModel> vehicleResponseModels = vehicleResponseMapper.entityToResponseModelList(vehicles);

        // Log the results of the mapping
        if (vehicleResponseModels == null) {
            log.warn("VehicleResponseModels is null after mapping");
        } else if (vehicleResponseModels.isEmpty()) {
            log.warn("VehicleResponseModels is empty after mapping");
        } else {
            log.info("Mapping successful. Number of VehicleResponseModels: {}", vehicleResponseModels.size());
        }

        return vehicleResponseModels;
    }

    @Override
    public VehicleResponseModel getVehicleByVehicleId(String customerId, String vehicleId) {

        Vehicle foundVehicle = vehicleRepository.findByCustomerIdAndVehicleIdentifier_VehicleId(customerId, vehicleId);

        // Check if the vehicle is null
        if (foundVehicle == null) {
            log.warn("No vehicle found for customer ID: {} and vehicle ID: {} (vehicle is null)", customerId, vehicleId);
            return null;
        }

        return vehicleResponseMapper.entityToResponseModel(foundVehicle);

    }

    @Override
    public VehicleResponseModel updateVehicleByVehicleId(VehicleRequestModel vehicleRequestModel, String customerId, String vehicleId) {
        Vehicle vehicleToUpdate = vehicleRepository.findByCustomerIdAndVehicleIdentifier_VehicleId(customerId, vehicleId);

        if (vehicleToUpdate == null) {
            return null; // Later throw exception
        }

        // Update vehicle details
        vehicleToUpdate.setMake(vehicleRequestModel.getMake());
        vehicleToUpdate.setModel(vehicleRequestModel.getModel());
        vehicleToUpdate.setYear(vehicleRequestModel.getYear());

        // Check for non-null transmission type and update after converting to enum
        TransmissionType transmissionType = vehicleRequestModel.getTransmissionType();
        if (transmissionType != null) {
            // Assuming the enum is valid and does not need to be converted from a string
            vehicleToUpdate.setTransmission_type(transmissionType);
        }

        vehicleToUpdate.setMileage(vehicleRequestModel.getMileage());
        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return vehicleResponseMapper.entityToResponseModel(updatedVehicle);
    }


    @Override
    public VehicleResponseModel addVehicleToCustomer(String customerId, VehicleRequestModel vehicleRequestModel) {
        CustomerAccount customerAccount = customerAccountRepository.findCustomerAccountByCustomerAccountIdentifier_CustomerId(customerId);

        if(customerAccount == null) {
            log.warn("Customer account not found for customer ID: {}", customerId);
            return null;
        }

        Vehicle newVehicle = new Vehicle();
        newVehicle.setVehicleIdentifier(new VehicleIdentifier());
        newVehicle.setCustomerId(vehicleRequestModel.getCustomerId());
        newVehicle.setMake(vehicleRequestModel.getMake());
        newVehicle.setModel(vehicleRequestModel.getModel());
        newVehicle.setYear(vehicleRequestModel.getYear());
        newVehicle.setTransmission_type(vehicleRequestModel.getTransmissionType());
        newVehicle.setMileage(vehicleRequestModel.getMileage());


        Vehicle savedVehicle = vehicleRepository.save(newVehicle);
        return vehicleResponseMapper.entityToResponseModel(savedVehicle);
    }

    @Override
    public void deleteAllVehiclesByCustomerId(String customerId) {
        List<Vehicle> vehicles = vehicleRepository.findAllByCustomerId(customerId);
        vehicles.forEach(vehicle -> { vehicleRepository.delete(vehicle);});
    }

}
