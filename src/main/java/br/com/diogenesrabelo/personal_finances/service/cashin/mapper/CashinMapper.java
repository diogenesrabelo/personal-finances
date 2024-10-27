package br.com.diogenesrabelo.personal_finances.service.cashin.mapper;

import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinRequest;
import br.com.diogenesrabelo.personal_finances.controller.cashin.dto.CashinResponse;
import br.com.diogenesrabelo.personal_finances.repository.cashin.entity.Cashin;
import br.com.diogenesrabelo.personal_finances.service.cashintype.mapper.CashinTypeMapper;

public class CashinMapper {

    public static Cashin requestToEntity(CashinRequest cashinRequest, String user) {
        return new Cashin().buildCashin(
            cashinRequest.date(),
            cashinRequest.value(),
            cashinRequest.description(),
            CashinTypeMapper.responseToEntity(cashinRequest.type(), user),
            user);
    }

    public static Cashin requestToEntity(CashinRequest cashinRequest, String user, Long id) {
        return new Cashin().buildCashinAllArgs(
            id,
            cashinRequest.date(),
            cashinRequest.value(),
            cashinRequest.description(),
            CashinTypeMapper.responseToEntity(cashinRequest.type(), user),
            user);
    }

    public static CashinResponse entityToResponse(Cashin cashin) {
        return new CashinResponse(
            cashin.getId(),
            cashin.getDate(),
            cashin.getValue(),
            CashinTypeMapper.entityToResponse(cashin.getType()),
            cashin.getDescription()
        );
    }
}
