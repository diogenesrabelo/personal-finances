package br.com.diogenesrabelo.personal_finances.service.cashintype.mapper;

import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashintype.dto.CashinTypeResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashintype.entity.CashinType;

public class CashinTypeMapper {

    public static CashinType requestToEntity(CashinTypeRequest cashinTypeRequest, String user) {
        return new CashinType().buildCashinType(
            cashinTypeRequest.name(),
            cashinTypeRequest.description(),
            cashinTypeRequest.active(),
            user
        );
    }

    public static CashinType requestToEntity(Long id, CashinTypeRequest cashinTypeRequest, String user) {
        return new CashinType().buildCashinTypeAllArgs(
            id,
            cashinTypeRequest.name(),
            cashinTypeRequest.description(),
            cashinTypeRequest.active(),
            user
        );
    }
    public static CashinType responseToEntity(CashinTypeResponse cashinTypeResponse, String user) {
        return new CashinType().buildCashinTypeAllArgs(
            cashinTypeResponse.id(),
            cashinTypeResponse.name(),
            cashinTypeResponse.description(),
            cashinTypeResponse.active(),
            user
        );
    }

    public static CashinTypeResponse entityToResponse(CashinType cashinType) {
        return new CashinTypeResponse(
            cashinType.getId(),
            cashinType.getName(),
            cashinType.getDescription(),
            cashinType.isActive()
        );
    }
}
