package com.dvmrabelo.personal_finances.core.usecases;

import com.dvmrabelo.personal_finances.core.domain.input.SaidaFinanceiraInput;
import com.dvmrabelo.personal_finances.core.domain.output.SaidaFinanceiraOutput;
import com.dvmrabelo.personal_finances.core.gateways.SaidaFinanceiraGateway;
import com.dvmrabelo.personal_finances.dataprovider.user.entity.UserEntity;
import com.dvmrabelo.personal_finances.dataprovider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidaFinanceiraUseCase {

    @Autowired
    private SaidaFinanceiraGateway saidaFinanceiraGateway;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('USER')")
    public List<SaidaFinanceiraOutput> findAll() {
        Long userId = getUser().getId();
        return saidaFinanceiraGateway.findAllByUserId(userId);
    }

    @PreAuthorize("hasRole('USER')")
    public SaidaFinanceiraOutput findById(Long id) {
        Long userId = getUser().getId();
        return saidaFinanceiraGateway.findByUserIdAndId(userId, id);
    }

    @PreAuthorize("hasRole('USER')")
    public SaidaFinanceiraOutput createSaidaFinanceira(SaidaFinanceiraInput saidaFinanceira) {
        UserEntity user = getUser();
        return saidaFinanceiraGateway.save(saidaFinanceira, user);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraUseCase.isUserSaidaFinanceiraOwner(#saidaId)")
    public SaidaFinanceiraOutput updateSaidaFinanceira(Long saidaId, SaidaFinanceiraInput newSaidaFinanceira) {
         return saidaFinanceiraGateway.update(newSaidaFinanceira, saidaId);
    }

    @PreAuthorize("hasRole('USER') and @saidaFinanceiraUseCase.isUserSaidaFinanceiraOwner(#saidaId)")
    public void deleteById(Long saidaId) {
        saidaFinanceiraGateway.deleteById(saidaId);
    }

    public boolean isUserSaidaFinanceiraOwner(Long documentId) {
        UserEntity user = getUser();

        SaidaFinanceiraOutput saidaFinanceiraOutput = saidaFinanceiraGateway.findById(documentId);
        return  saidaFinanceiraOutput.createdBy().getId().equals(user.getId());
    }

    private UserEntity getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;
        if (principal instanceof UserDetails) {
            name = ((UserDetails)principal).getUsername();
            var user = userRepository.findByUsername(name);
            if (user.isEmpty()) {
                throw new RuntimeException("Usuário não encontrado.");
            }
            return user.get();
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}
