package com.library_a3.library_a3.services.reserve;

import java.util.List;

import com.library_a3.library_a3.shared.enums.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.Claim;
import com.library_a3.library_a3.domains.Reserve;
import com.library_a3.library_a3.repositories.ReserveRepository;
import com.library_a3.library_a3.services.TokenService;

@Service
public class GetReservesService {

   @Autowired
   private TokenService tokenService;
   @Autowired
   private ReserveRepository reserveRepository;

   public List<Reserve> execute(String token){

       Claim authority = this.tokenService.getAuthority(token);
       if(authority.asString().equals(Role.EMPLOYEE.toString())) {
           return this.reserveRepository.findAllReserves();
       }

       Claim studentId = this.tokenService.getUserId(token);
       return this.reserveRepository.findByStudentId(studentId.asString());
   };

}
