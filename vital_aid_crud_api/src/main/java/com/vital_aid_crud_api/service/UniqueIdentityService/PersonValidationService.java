package com.vital_aid_crud_api.service.UniqueIdentityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.Exception.DuplicateEntryException;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.UserRepository;

@Service
public class PersonValidationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validateUniqueFields(String personEmail, String personPhone) {
        // Check for email uniqueness
        if (userRepository.findByPersonEmail(personEmail).isPresent() || adminRepository.findByPersonEmail(personEmail).isPresent()
                                || doctorRepository.findByPersonEmail(personEmail).isPresent()) {
            throw new DuplicateEntryException("Email already exists.");
        }

        // Check for password uniqueness
        // validateUniquePassword(loginPassword);

        // Check for phone uniqueness
        if (userRepository.findByPersonPhone(personPhone).isPresent() || adminRepository.findByPersonPhone(personPhone).isPresent() || 
                                doctorRepository.findByPersonPhone(personPhone).isPresent()) {
            throw new DuplicateEntryException("Phone number already exists.");
        }

        
    }

    // IMPORTANT: This method is not used in the project. It is only here to show the difference between the two methods.
    // public void validateUniqueFieldsForUpdate(String personEmail, String personPhone, String loginPassword, Long personId, boolean isAdmin) {
    //     // Check for email uniqueness (excluding the current record)
    //     if ((isAdmin && adminRepository.findByPersonEmailAndPersonIdNot(personEmail, personId).isPresent())
    //             || (!isAdmin && userRepository.findByPersonEmailAndPersonIdNot(personEmail, personId).isPresent())) {
    //         throw new DuplicateEntryException("Email already exists.");
    //     }

    //     // Check for phone uniqueness (excluding the current record)
    //     if ((isAdmin && adminRepository.findByPersonPhoneAndPersonIdNot(personPhone, personId).isPresent())
    //             || (!isAdmin && userRepository.findByPersonPhoneAndPersonIdNot(personPhone, personId).isPresent())) {
    //         throw new DuplicateEntryException("Phone number already exists.");
    //     }

    //     // Check for password uniqueness
    //     validateUniquePasswordForUpdate(loginPassword, personId, isAdmin);
    // }

    public void validateUniqueFieldsForUpdate(String personEmail, String personPhone,Long personId) {
        // Check for email uniqueness (excluding the current record)
        if ((adminRepository.findByPersonEmailAndPersonIdNot(personEmail, personId).isPresent())
                || (userRepository.findByPersonEmailAndPersonIdNot(personEmail, personId).isPresent())
                                || (doctorRepository.findByPersonEmailAndPersonIdNot(personEmail, personId).isPresent())) {
            throw new DuplicateEntryException("Email already exists.");
        }

        // Check for password uniqueness
        // validateUniquePasswordForUpdate(loginPassword, personId); // IMPORTANT: This method is not used in the project. It is only here to show the difference between the two methods.
        
        // Check for phone uniqueness (excluding the current record)
        if ((adminRepository.findByPersonPhoneAndPersonIdNot(personPhone, personId).isPresent())
                || (userRepository.findByPersonPhoneAndPersonIdNot(personPhone, personId).isPresent()) ||
                                (doctorRepository.findByPersonPhoneAndPersonIdNot(personPhone, personId).isPresent())) {
            throw new DuplicateEntryException("Phone number already exists.");
        }

        
    }

    // private void validateUniquePassword(String loginPassword) {
    //     // Check in users
    //     if (userRepository.findAll().stream()
    //             .anyMatch(user -> bCryptPasswordEncoder.matches(loginPassword, user.getLoginPassword()))) {
    //         throw new DuplicateEntryException("Password already exists.");
    //     }

    //     // Check in admins
    //     if (adminRepository.findAll().stream()
    //             .anyMatch(admin -> bCryptPasswordEncoder.matches(loginPassword, admin.getLoginPassword()))) {
    //         throw new DuplicateEntryException("Password already exists.");
    //     }
    // }


    // IMPORTANT: This method is not used in the project. It is only here to show the difference between the two methods.
    // private void validateUniquePasswordForUpdate(String loginPassword, Long personId, boolean isAdmin) {
    //     // Check in users (excluding the current user)
    //     if (!isAdmin && userRepository.findAll().stream()
    //             .filter(user -> !user.getPersonId().equals(personId))
    //             .anyMatch(user -> bCryptPasswordEncoder.matches(loginPassword, user.getLoginPassword()))) {
    //         throw new DuplicateEntryException("Password already exists.");
    //     }

    //     // Check in admins (excluding the current admin)
    //     if (isAdmin && adminRepository.findAll().stream()
    //             .filter(admin -> !admin.getPersonId().equals(personId))
    //             .anyMatch(admin -> bCryptPasswordEncoder.matches(loginPassword, admin.getLoginPassword()))) {
    //         throw new DuplicateEntryException("Password already exists.");
    //     }
    // }

    // private void validateUniquePasswordForUpdate(String loginPassword, Long personId) {
        // Check in users (excluding the current user)
        // if (userRepository.findAll().stream()
        //         .filter(user -> !user.getPersonId().equals(personId))
        //         .anyMatch(user -> bCryptPasswordEncoder.matches(loginPassword, user.getLoginPassword()))) {
        //     throw new DuplicateEntryException("Password already exists.");
        // }

        // // Check in admins (excluding the current admin)
        // if (adminRepository.findAll().stream()
        //         .filter(admin -> !admin.getPersonId().equals(personId))
        //         .anyMatch(admin -> bCryptPasswordEncoder.matches(loginPassword, admin.getLoginPassword()))) {
        //     throw new DuplicateEntryException("Password already exists.");
        // }

}
