package service;

import model.CompanyRole;
import model.CrewMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CompanyRoleRepository;
import repository.CrewRepository;

import java.util.List;

@Service
public class CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private CompanyRoleRepository companyRoleRepository;


    Logger logger = LoggerFactory.getLogger(CrewService.class);

    public boolean insert(CrewMember member) {
        return crewRepository.save(member) != null; //В ОБщем виде - обьект подготовить ранее.
    }

    public List<CrewMember> get(String name) {
        return crewRepository.findByName(name);
    }

    public CrewMember get(Integer id) {
        return crewRepository.findOne(id);
    }

    public List<CrewMember> getAll() {
        return crewRepository.findAllByOrderByIdAsc();
    }

    public List<CrewMember> getAllFreeByFunction(String functionName) {
        List<CrewMember> result = crewRepository.findAllByFlightDoneAndFunctionName(true, functionName);
        result.addAll(crewRepository.findAllByFunctionNameEqualsAndFlightNull(functionName));
        return result;
    }

    public List<CrewMember> getAllByFlight(Integer id) {
        return crewRepository.findAllByFlightIdEqualsOrderByFunctionId(id);
    }

    public List<CompanyRole> getAllCompanyRoles() {
        return companyRoleRepository.findAllByOrderByIdAsc();
    }

    public CompanyRole getCompanyRole(Integer id) {
        return companyRoleRepository.findOne(id);
    }

    @Transactional
    public boolean delete(Integer id) {
        CrewMember member = crewRepository.findOne(id);
        if (member == null || (member.getFlight() != null && !member.getFlight().isDone()))
            return false;
        crewRepository.delete(id);
        return true;
    }


}
