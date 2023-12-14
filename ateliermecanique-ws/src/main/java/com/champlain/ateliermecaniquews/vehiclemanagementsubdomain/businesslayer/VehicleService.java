package com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.businesslayer;

import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.presentationlayer.VehicleRequestModel;
import com.champlain.ateliermecaniquews.vehiclemanagementsubdomain.presentationlayer.VehicleResponseModel;

import java.util.List;

public interface VehicleService {

    List<VehicleResponseModel> getAllVehiclesForCustomer(String customerId);

    VehicleResponseModel getVehicleByVehicleId(String customerId, String vehicleId);
    VehicleResponseModel updateVehicleByVehicleId(VehicleRequestModel vehicleRequestModel, String customerId, String vehicleId);




    void deleteAllVehiclesByCustomerId(String customerId);
}
