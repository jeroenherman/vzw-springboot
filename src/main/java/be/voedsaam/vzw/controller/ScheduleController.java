package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.service.ScheduleService;
import be.voedsaam.vzw.service.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private ScheduleMapper scheduleMapper;
    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @Autowired
    public void setScheduleMapper(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @RequestMapping({"list" ,"/"})
    public String listSchedules(Model model){
        model.addAttribute("schedules" ,scheduleMapper.mapToDTO((List<Schedule>)scheduleService.listAll()));
        return "schedule/list";
    }

}
