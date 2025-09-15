package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.ConsultationDTO;
import edu.ijse.drivingschool.dto.CoordinatorDTO;

import java.util.List;

public interface CoordinatorBO extends SuperBO {

    public boolean save(CoordinatorDTO coordinatorDTO);
    public boolean update(CoordinatorDTO coordinatorDTO);
    public boolean delete(String id);
    public List<CoordinatorDTO> getAll();

}
