package com.institutosemprealerta.semprealerta.domain.model;

import lombok.Builder;
@Builder
public class File {
        private String fileName;
        private String fileDownloadUri;
        private String fileType;

        public String getFileName() {
                return fileName;
        }

        public void setFileName(String fileName) {
                this.fileName = fileName;
        }

        public String getFileDownloadUri() {
                return fileDownloadUri;
        }

        public void setFileDownloadUri(String fileDownloadUri) {
                this.fileDownloadUri = fileDownloadUri;
        }

        public String getFileType() {
                return fileType;
        }

        public void setFileType(String fileType) {
                this.fileType = fileType;
        }
}
