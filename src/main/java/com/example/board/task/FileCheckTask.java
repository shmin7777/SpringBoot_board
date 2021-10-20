package com.example.board.task;

import com.example.board.beans.dao.AttachFileDAO;
import com.example.board.beans.vo.AttachFileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileCheckTask {
//    Quartz format
//    [Seconds] [Minutes] [Hours] [Day-of-Month] [Month] [Day-of-Week] [Year-생략가능]
//    - Seconds : 0 ~ 59
//    - Minutes : 0 ~ 59
//    - Hours : 0 ~ 23
//    - Day of Month : 0 ~ 31
//    - Month : 1 ~ 12
//    - Day of Week : 1(Sunday) ~ 7(Saturday)

//    *     모든 수를 나타낸다
//    -     값의 사이를 지정한다.    "* 10-13 * * * *"       10, 11, 12, 13분에 동작한다.
//    ,     특정 값을 지정한다.     "* 10,11,13 * * * *"     10, 11, 13분에 동작한다.
//    /     값의 증가를 표현한다.    "* 0/5 * * * *"         0분부터 5분마다 동작
//    ?     특별한 값이 없음을 나타낸다.    day-of-month, day-of-week 필드만 사용 가능, 일자, 요일에 하나만 설정할 때 나머지에 지정한다.
//    L     마지막 날을 나타낸다.    day-of-month, day-of-week 필드만 사용 가능, 일자 필드에 사용되면 이달의 마지막 일자이다.
//                                day-of-month : L-3 : 이달의 마지막날 3일 전부터 마지막 날까지
//                                day-of-week  : L-3 : 목요일부터 토요일까지를 나타낸다.
//                                day-of-week  : 6L or FRIL
//    W     주어진 날로부터 가장 가까운 평일(월~금)을 나타낸다.
//    #     이달의 n번째 x요일을 나타낸다. 6#3 or FRIL은 이달의 세 번째 금요일을 나타낸다.

//    매 분 0초마다  : 0 * * * * * *
//    매 1초 간격    : 0/1 * * * * ?
//    매 1분 간격   : 0 0/1 * * * ?
//    매 5분 간격   : 0 0/5 * ?
//    매 1시간 간격 : 0 0 0/1 * * ?
//    매일 0시 마다 : 0 0 0 * * ?
//    매월 1일 마다 : 0 0 0 1 * ?

    @Autowired
    private AttachFileDAO attachFileDAO;

    @Scheduled(cron = "* * 2 * * *")
    public void checkFiles() {
        log.warn("File check task run...............");
        log.warn("----------------------------------");

        //어제 첨부파일 목록
        List<AttachFileVO> fileVOList = attachFileDAO.getOldFiles();

        //원본 경로
        List<Path> fileListPaths = fileVOList.stream().map(attach ->
                Paths.get("C:/upload", attach.getUploadPath(), attach.getUuid() + "_" + attach.getFileName())
        ).collect(Collectors.toList());

        //썸네일 경로를 원본 경로 List에 추가
        fileVOList.stream().filter(attach -> attach.isImage()).map(attach ->
                Paths.get("C:/upload", attach.getUploadPath(), "s_" + attach.getUuid() + "_" + attach.getFileName()))
                .forEach(path -> fileListPaths.add(path));

        //어제 업로드 된 폴더의 경로
        File dir = Paths.get("C:/upload", getFolderYesterday()).toFile();

        //DB에 있는 파일 경로와 실제 경로의 파일을 비교하여 일치하지 않는 것만 removeFiles에 담아준다.
        File[] removeFiles = dir.listFiles(file -> !fileListPaths.contains(file.toPath()));

        for(File file : removeFiles){
            log.warn(file.getPath() + " deleted");
            file.delete();
        }
    }

    private String getFolderYesterday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        //현재 날짜에서 -1은 하루 전날을 의미한다.
        cal.add(Calendar.DATE, -1);
        String str = sdf.format(cal.getTime());

        return str.replace("-", "/");
    }
}
