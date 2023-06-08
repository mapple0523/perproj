package com.example.personal.controller;


import com.example.personal.dto.ReviewDto;
import com.example.personal.dto.UserDto;
import com.example.personal.entity.User;
import com.example.personal.service.MatchingService;
import com.example.personal.service.ReviewService;
import com.example.personal.service.TrainerService;
import com.example.personal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/main")
@Controller
public class MainController {

    private final TrainerService trainerService;

    private final UserService userService;

    private final ReviewService reviewService;

    private final MatchingService matchingService;

    @GetMapping("/login")
    public void login() {
        log.info("login------");
    }

    @GetMapping("/signIn")
    public void signIn() {
        log.info("signIn------");
    }

    @GetMapping("/main")
    public void main(){}

    @GetMapping("/addReview")
    public void addReview(){}


    @PostMapping(value = "/login")
    public String signin(UserDto userDto, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        log.info("{}", userDto);

        var dto = userService.login(userDto);

        if (dto == null) {
            redirectAttributes.addFlashAttribute("status", HttpStatus.BAD_REQUEST.toString());
            return "main/error";
        }
        log.info("dto----------{}", dto);
        HttpSession session = request.getSession();
        session.setAttribute("Login", dto);

        redirectAttributes.addFlashAttribute("userinfo", dto);

        return "main/main";
    }

    @GetMapping("/logout")
    public String logoutV3(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "main/login";
    }

    @PostMapping("/signIn")
    public ResponseEntity regiserUser(UserDto userDto) {
        log.info("save to user = {}", userDto);
        var dto = userService.signIn(userDto);

        if (dto.getIdx() != null) {
            dto.setHttpStatus("OK");
            return ResponseEntity.ok(dto);
        }
        dto.setHttpStatus("FAIL");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @PostMapping(value="/addReview")
    public ResponseEntity addReview(@RequestPart(required = false) @RequestParam ReviewDto reviewDto){
        log.info("save to user = {}", reviewDto);
        var dto = reviewService.addReview(reviewDto);

        if(dto.getIdx() != null) {
            dto.setHttpStatus("OK");
            return ResponseEntity.ok(dto);

        }
        dto.setHttpStatus("FAIL");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
    }

    @Transactional
    @GetMapping(value = "/find")
    public ResponseEntity findTrainer(@RequestParam String location, Model model, RedirectAttributes redirectAttributes) {

        log.info("{}", location);

        var a = trainerService.trainerLocationFind(location);


        return ResponseEntity.ok(a);
    }

    @GetMapping(value = "/review")
    public ResponseEntity review() {

        var a = reviewService.findAllReview();

        return ResponseEntity.ok(a);
    }

    @GetMapping(value = "/trainer-idx")
    public String trainerFind(@RequestParam Long idx, RedirectAttributes redirectAttributes) {
        log.info("idx={}------------------", idx);

        var data = trainerService.trainerIdFind(idx);

        log.info("data={}----------", data);

        return "main/trainer";
    }

    @Transactional
    @GetMapping(value = "/trainer-find")
    public ResponseEntity trainerFind(@RequestParam Long idx) {
        log.info("idx------------------{}", idx);

        var data = trainerService.trainerIdFind(idx);

        return ResponseEntity.ok(data);
    }

    @ResponseBody
    @Transactional
    @GetMapping(value = "/matching")
    public ResponseEntity matching(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time, @RequestParam Long idx) throws IOException {
//        log.info("time------------------{}",time);
//        log.info("idx---------------------{}",idx);
//        log.info("localtime---------------{}",localTime);


        LocalTime localTime = LocalTime.from(time);

        //트레이너 찾기 완료

        var trainer = trainerService.findTrainer(localTime, idx);

        //매칭하기,로그인한 유저 정보 받아야함 완료

        HttpSession session = request.getSession();
        Object name = session.getAttribute("Login");
        var userIdx = Long.parseLong(name.toString().substring(29, 30));
        log.info("{}", userIdx);

        var duplicate = matchingService.findTimeAndUser(time, userIdx);
        var dupTime = matchingService.findTime(time);
//        log.info("duplicate------{}",duplicate);
//        log.info("trainer------{}",trainer);


        //유저가 선택한 시간과 유저가 같으면 안되게하는 service 필요


        String message = "";
//        매칭한값을 트레이너에 넣기 -완료

        if (!trainer.isEmpty() && !duplicate.isEmpty() && !dupTime.isEmpty()) {
            log.info("duplicate-------");
            return ResponseEntity.ok("이미 예약하신 시간입니다.");
        } else if(!trainer.isEmpty() && !dupTime.isEmpty()) {
            log.info("duplicate-user-time");
            return ResponseEntity.ok("이미 예약된 시간입니다.");
        }
        else if (trainer.isEmpty()) {
            log.info("trainer not time-------");
//            message = "<script>alert('매칭완료');location.href='/trainer-idx.do';</script>";
            return ResponseEntity.ok("시간이 맞지 않습니다.");
        } else {
            matchingService.addMatching(time, userIdx, idx);
            log.info("save");
            return ResponseEntity.ok("매칭 완료");
        }

        //        log.info("trainer---{}",trainer);

    }

    @GetMapping(value="/user-info")
    public void userInfo(){

    }

    @GetMapping(value="/user-infomation")
    public ResponseEntity userInformation(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object name = session.getAttribute("Login");
        var userIdx = Long.parseLong(name.toString().substring(29, 30));
        log.info("{}", userIdx);

        var matchingTrainer = matchingService.findTrainerAndMatching(userIdx);

        log.info("{}",matchingTrainer);

        return ResponseEntity.ok(matchingTrainer);
    }

//    @GetMapping(value="/user-infoma")
//    public ResponseEntity userInfoma(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Object name = session.getAttribute("Login");
//        var userIdx = Long.parseLong(name.toString().substring(29, 30));
//        log.info("{}", userIdx);
//
//        var a = userService.findUser(userIdx);
//        return ResponseEntity.ok(a);
//    }

    @Transactional
    @GetMapping(value="matching-delete")
    public void matchingDelete(@RequestParam Long idx) {

        log.info("matchingidx---------------{}",idx);
        matchingService.delete(idx);
    }
}
