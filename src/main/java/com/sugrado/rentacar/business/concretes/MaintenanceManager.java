package com.sugrado.rentacar.business.concretes;

import com.sugrado.rentacar.business.abstracts.MaintenanceService;
import com.sugrado.rentacar.business.dtos.requests.CreateMaintenanceRequest;
import com.sugrado.rentacar.business.dtos.responses.CreatedMaintenanceResponse;
import com.sugrado.rentacar.core.utilities.mapping.ModelMapperService;
import com.sugrado.rentacar.dataAccess.abstracts.MaintenanceRepository;
import com.sugrado.rentacar.entities.concretes.Maintenance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private MaintenanceRepository maintenanceRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedMaintenanceResponse add(CreateMaintenanceRequest createMaintenanceRequest) {
        Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);

        maintenance.setCreatedDate(LocalDateTime.now());
        maintenance.setDateSent(LocalDateTime.now());

        Maintenance createdMaintenance = maintenanceRepository.save(maintenance);

        return this.modelMapperService.forResponse()
                .map(createdMaintenance, CreatedMaintenanceResponse.class);
    }
}
