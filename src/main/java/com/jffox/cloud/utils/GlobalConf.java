package com.jffox.cloud.utils;

/**
 * Created by Frank on 2017/1/17.
 */
public class GlobalConf {

    public enum ErrorType {
        SET_HBASE_ROWKEY_EMPTY("sethbaserowkey in this record is empty."),
        DATABASE_EMPTY("Database in this record is empty. "),
        TABLE_NAME_EMPTY("Table name in this record is empty."),
        ACTIION_TYPE_EMPTY("Action type in this record is empty."),
        DATA_EMPTY("Data to be processed in this record is empty."),
        PARSE_ERROR("Parse data in this record error! "),
        JSON_PARSER_ERROR("Parse json data error! "),
        GENERATE_ROWKEY_ERROR("generate rowkey failed! "),
        GENERATE_ORIGINAL_ROWKEY_ERROR("generate original rowkey failed! "),
        JSON_DATA_EMPTY("Json Data in this record is empty"),
        DATA_NOT_COMPLETE("Json Data in this record is not complete data"),
        ZHIMA_NEW_RISK_EMPTY("Data in this record getZhimaNewRisk() is null！"),

        SOURCE_Id_EMPTY("generate rowkey parameter sysSourceId is empty"),
        SOURCE_USER_Id_EMPTY("generate rowkey parameter getSourceUserId is empty"),
        GET_SYS_CODE_EMPTY("get syscode null by syscodeMap is empty"),
        SOURCE_USER_ID_LENGTH("getSourceUserId,length is exceed 18"),

        ORIGINAL_SOURCE_Id_EMPTY("generate original rowkey parameter sysSourceId is empty"),
        ORIGINAL_SOURCE_USER_Id_EMPTY("generate original rowkey parameter getSourceUserId is empty"),
        TRANS_NO_EMPTY("generate original rowkey parameter TRANS_NO is empty"),
        ORIGINAL_GET_SYS_CODE_EMPTY("get original syscode null by syscodeMap is empty"),
        SCORE_RISK_EMPTY("Zhima json data neither belong to zhima score nor belong to risk"),
        RISK_DETAILS_EXCEPTION("Zhima risk Details handle exception"),
        APIKEY_CUSTOMERID_ERROR("Could not find customerId or apiKey in this record"),

        ADD_UPDATE_ES_INDEX_FAILED("Create es index in this record failed."),
        DELETE_ES_INDEX_FAILED("Delete es index in this record failed."),
        CREATE_ES_INDEX_FAILED("create es index failed "),
        DATABASE_TABLE_ERROR("Could not find database or table in this record"),
        UNKNOW_ERROR("System unknown error.");
        


        private String desc;
        ErrorType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

    }
}
