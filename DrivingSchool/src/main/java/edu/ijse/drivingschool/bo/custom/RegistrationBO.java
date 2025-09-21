package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.RegistrationDTO;

import java.util.List;

public interface RegistrationBO extends SuperBO {

    public String getNextId() throws Exception;
    public boolean save(RegistrationDTO registrationDTO);
    public boolean update(RegistrationDTO registrationDTO);
    public boolean delete(String id);
    public List<RegistrationDTO> getAll();

}
