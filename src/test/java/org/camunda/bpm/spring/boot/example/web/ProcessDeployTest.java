package org.camunda.bpm.spring.boot.example.web;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("application")
public class ProcessDeployTest {
    @Autowired
    private RepositoryService repositoryService; //流程定义服务对象

    @Test
    public void processDeploy() {
        //读取流程文件，获取文件输入流
        String fileName = "diagram_1.bpmn";
        InputStream inputStream = ProcessDeployTest.class.getResourceAsStream("/bpmn/" + fileName);
        //获取Deployment对象
        Deployment deployment = repositoryService.createDeployment()
                .name("diagram_1")
                .addInputStream(fileName, inputStream)
                .deploy();

        if (deployment != null) {
            System.out.println("部署id:" + deployment.getId());
        }

    }

    @Test
    public void getProcessDefInfo() {
        String pdKey = "test";//此处流程定义key为设计流程图时的Id值
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
                .active()
                .processDefinitionKey(pdKey)
                .latestVersion()//获取最新版本
                .singleResult();

        if (pd != null) {
            System.out.println("流程定义id:" + pd.getId() + ",流程版本：" + pd.getVersion());
        }


    }

}
