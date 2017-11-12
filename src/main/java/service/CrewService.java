package service;

import model.CompanyRole;
import model.CrewMember;
import model.CrewMemberVacation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CompanyRoleRepository;
import repository.CrewRepository;

import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class CrewService {

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private CompanyRoleRepository companyRoleRepository;


    Logger logger = LoggerFactory.getLogger(CrewService.class);

    @Transactional
    public boolean constructInsert(String name, String start, String end, String companyRole) {
        CompanyRole crewMemberRole;
        List<CompanyRole> companyRoleList = companyRoleRepository.findByName(companyRole);
        CrewMemberVacation vac;
        try {
            vac = new CrewMemberVacation(start, end);
            if (companyRoleList.isEmpty()) {
                crewMemberRole = new CompanyRole();
                crewMemberRole.setName(companyRole);
                companyRoleRepository.save(crewMemberRole);
            } else {
                crewMemberRole = companyRoleList.get(0);
            }
        } catch (ParseException e1) {
            logger.info("date parse fail");
            return false;
        }
        return crewRepository.save(new CrewMember(name, crewMemberRole, vac)) != null;
    }


    @Transactional
    public boolean insert(CrewMember member) {
        CompanyRole crewMemberRole;
        List<CompanyRole> companyRoleList = companyRoleRepository.findByName(member.getFunction().getName());
        if (companyRoleList.isEmpty()) {
            crewMemberRole = new CompanyRole();
            crewMemberRole.setName(member.getFunction().getName());
            crewMemberRole = companyRoleRepository.save(crewMemberRole);
        } else {
            crewMemberRole = companyRoleList.get(0);
        }
        member.setFunction(crewMemberRole);
        return crewRepository.save(member) != null;
    }

    public List<CrewMember> get(String name) {
        return crewRepository.findByName(name);
    }

    public CrewMember get(Integer id) {
        return crewRepository.findOne(id);
    }

    @Transactional
    public boolean update (CrewMember member) {
        CrewMember old = crewRepository.findOne(member.getId());
        if (old == null)
            return false;
        old.setName(member.getName());
        old.setVacation(member.getVacation());
        old.setFunction(member.getFunction());
        return crewRepository.save(old) != null;
    }

    @Transactional
    public boolean delete (CrewMember member) {
        CrewMember old = crewRepository.findOne(member.getId());
        if (old == null)
            return false;
        crewRepository.delete(old.getId());
        return true;
    }


}
