package edu.ijse.drivingschool.bo.custom;

import edu.ijse.drivingschool.bo.SuperBO;
import edu.ijse.drivingschool.dto.ConsultationDTO;
import edu.ijse.drivingschool.entity.Consultation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface ConsultationBO extends SuperBO {

    public boolean save(ConsultationDTO consultationDTO);
    public boolean update(ConsultationDTO consultationDTO);
    public boolean delete(String id);
    public List<ConsultationDTO> getAll();

}
