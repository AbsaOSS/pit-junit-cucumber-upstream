package africa.za.gambit.architecture;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.tools.dao.MqRequestDAO;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.File;

import static io.restassured.RestAssured.given;

@Log4j
public class MqOperations extends TestBase {

    private MqOperations() {
        throw new IllegalStateException("Utility class");
    }

    private static final String MQ_USERNAME = frameworkProperties.get("framework.mq.whitelisted-svc-username");
    private static final String MQ_PASSWORD = frameworkProperties.get("framework.mq.whitelisted-svc-password");

    private static final String MQ_URL = frameworkProperties.get("framework.mq.url");
    private static final String MQ_PUT = frameworkProperties.get("framework.mq.put-operation");
    private static final String MQ_CLEAR = frameworkProperties.get("framework.mq.clear-operation");
    private static final String MQ_FIND = frameworkProperties.get("framework.mq.find-operation");
    private static final String MQ_PUT_FILE = frameworkProperties.get("framework.mq.putfile-operation");

    public static Response mqPut(String host, int port, String queueManager, String channel, String userName, String password, String queueName, String message) {
        MqRequestDAO dao = new MqRequestDAO().build(host, port, queueManager, channel, userName, password, queueName);
        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_PUT;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .param("Message", message)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqPutFile(String host, int port, String queueManager, String channel, String userName, String password, String queueName, File file, String separator) {
        if (file.isDirectory()) {
            throw new RuntimeException("Directory file paths are not allowed");
        }
        MqRequestDAO dao = new MqRequestDAO().build(host, port, queueManager, channel, userName, password, queueName);
        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_PUT_FILE;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .param("Separator", separator)
                .multiPart("File", file)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqClear(String host, int port, String queueManager, String channel, String userName, String password, String queueName) {
        MqRequestDAO dao = new MqRequestDAO().build(host, port, queueManager, channel, userName, password, queueName);
        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_CLEAR;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqFind(String host, int port, String queueManager, String channel, String userName, String password, String queueName, int maxWaitTime, String message) {
        MqRequestDAO dao = new MqRequestDAO().build(host, port, queueManager, channel, userName, password, queueName);
        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_FIND;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .param("MaxWaitTime", maxWaitTime)
                .param("Message", message)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqPut(Systems systems, String queueName, String message) {

        MqRequestDAO dao = getSystemMqRequestDAO(systems, queueName);

        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_PUT;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .param("Message", message)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqPutFile(Systems systems, String queueName, File file, String separator) {

        if (file.isDirectory()) {
            throw new RuntimeException("Directory file paths are not allowed");
        }

        MqRequestDAO dao = getSystemMqRequestDAO(systems, queueName);

        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_PUT_FILE;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .param("Separator", separator)
                .multiPart("File", file)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqClear(Systems systems, String queueName) {

        MqRequestDAO dao = getSystemMqRequestDAO(systems, queueName);

        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_CLEAR;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());
        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    public static Response mqFind(Systems systems, String queueName, int maxWaitTime, String message) {

        MqRequestDAO dao = getSystemMqRequestDAO(systems, queueName);

        RestAssured.baseURI = MQ_URL;
        RestAssured.basePath = MQ_FIND;
        RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponse());

        Response response = given()
                .relaxedHTTPSValidation()
                .param("MqRequestParameters", dao)
                .param("MaxWaitTime", maxWaitTime)
                .param("Message", message)
                .log().uri().log().method().log().parameters()
                .post()
                .thenReturn();
        log.info("Response Log");
        response.getBody().prettyPrint();
        return response;
    }

    /**
     * @implNote Do not delete. This is a connection example and all mq instance connection details are to be added below.
     */
    private static MqRequestDAO getSystemMqRequestDAO(Systems systems, String queueName) {
        MqRequestDAO dao = new MqRequestDAO();
        dao.setUser(System.getProperty("user.name").toLowerCase());

        int port = 0;

        switch (systems) {
            case PAF:
                dao.setHost(environmentProperties.get("paf.mq.hostname"));
                if (environmentProperties.get("paf.mq.port") != null) {
                    port = Integer.parseInt(environmentProperties.get("paf.mq.port"));
                }
                dao.setQueueManager(environmentProperties.get("paf.mq.queuemanager"));
                dao.setChannel(environmentProperties.get("paf.mq.channel"));
                dao.setUserName(environmentProperties.get("paf.mq.username"));
                dao.setPassword(environmentProperties.get("paf.mq.password"));
                break;

            default:
                throw new RuntimeException("The System [" + systems + "] is not catered for");
        }
        // Queue name for framework across environments is: TEST.SIT.FRAMEWORK.Q
        if (environment.equalsIgnoreCase("uat")) {
            switch (systems) {
                default:
                    dao.setUserName(MQ_USERNAME);
                    dao.setPassword(MQ_PASSWORD);
                    break;
            }
        }
        dao.setPort(port);
        dao.setQueueName(queueName);
        return dao;
    }

    @AllArgsConstructor
    public enum Systems {

        PAF("paf");

        @Getter
        private final String value;
    }

}