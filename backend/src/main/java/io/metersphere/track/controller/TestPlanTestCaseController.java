package io.metersphere.track.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.metersphere.base.domain.TestPlanTestCase;
import io.metersphere.base.domain.TestPlanTestCaseWithBLOBs;
import io.metersphere.commons.utils.PageUtils;
import io.metersphere.commons.utils.Pager;
import io.metersphere.track.request.testcase.TestPlanCaseBatchRequest;
import io.metersphere.track.request.testplancase.QueryTestPlanCaseRequest;
import io.metersphere.track.dto.TestPlanCaseDTO;
import io.metersphere.track.service.TestPlanTestCaseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/test/plan/case")
@RestController
public class TestPlanTestCaseController {

    @Resource
    TestPlanTestCaseService testPlanTestCaseService;

    @PostMapping("/list/{goPage}/{pageSize}")
    public Pager<List<TestPlanCaseDTO>> getTestPlanCases(@PathVariable int goPage, @PathVariable int pageSize, @RequestBody QueryTestPlanCaseRequest request){
        Page<Object> page = PageHelper.startPage(goPage, pageSize, true);
        return PageUtils.setPageInfo(page, testPlanTestCaseService.list(request));
    }

    @GetMapping("/get/{caseId}")
    public TestPlanCaseDTO getTestPlanCases(@PathVariable String caseId){
        return testPlanTestCaseService.get(caseId);
    }

    @PostMapping("recent/{count}")
    public List<TestPlanCaseDTO> getRecentTestCases(@PathVariable int count, @RequestBody QueryTestPlanCaseRequest request){
        return testPlanTestCaseService.getRecentTestCases(request, count);
    }

    @PostMapping("pending/{count}")
    public List<TestPlanCaseDTO> getPrepareTestCases(@PathVariable int count, @RequestBody QueryTestPlanCaseRequest request){
        return testPlanTestCaseService.getPendingTestCases(request, count);
    }

    @PostMapping("/list/all")
    public List<TestPlanCaseDTO> getTestPlanCases(@RequestBody QueryTestPlanCaseRequest request){
        return testPlanTestCaseService.list(request);
    }

    @PostMapping("/edit")
    public void editTestCase(@RequestBody TestPlanTestCaseWithBLOBs testPlanTestCase){
        testPlanTestCaseService.editTestCase(testPlanTestCase);
    }

    @PostMapping("/batch/edit")
    public void editTestCaseBath(@RequestBody TestPlanCaseBatchRequest request){
        testPlanTestCaseService.editTestCaseBath(request);
    }

    @PostMapping("/delete/{id}")
    public int deleteTestCase(@PathVariable String id){
        return testPlanTestCaseService.deleteTestCase(id);
    }

}