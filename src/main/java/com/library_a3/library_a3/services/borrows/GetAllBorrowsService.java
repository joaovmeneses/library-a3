package com.library_a3.library_a3.services.borrows;

import com.auth0.jwt.interfaces.Claim;
import com.library_a3.library_a3.domains.Borrow;
import com.library_a3.library_a3.repositories.BorrowRepository;
import com.library_a3.library_a3.services.TokenService;
import com.library_a3.library_a3.shared.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBorrowsService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private BorrowRepository borrowRepository;
    public List<Borrow> execute(String token) {

        Claim authority = this.tokenService.getAuthority(token);
        if(authority.asString().equals(Role.EMPLOYEE.toString())) {
            return this.borrowRepository.findAllWithRelationship();
        }
        Claim studentId = this.tokenService.getUserId(token);
        return this.borrowRepository.findByStudentId(studentId.asString());
    };
}
