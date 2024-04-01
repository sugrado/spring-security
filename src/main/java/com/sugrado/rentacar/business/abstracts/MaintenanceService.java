package com.sugrado.rentacar.business.abstracts;

import com.sugrado.rentacar.business.dtos.requests.CreateMaintenanceRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedMaintenanceResponse;

public interface MaintenanceService {
    CreatedMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest);
}
