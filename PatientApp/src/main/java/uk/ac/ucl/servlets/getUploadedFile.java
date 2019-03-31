package uk.ac.ucl.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@WebServlet("/fileUploaded.html")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class getUploadedFile extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String UPLOAD_DIR = "uploadedFiles";
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String appPath = getServletContext().getRealPath("");
        String upPath = appPath + File.separator + UPLOAD_DIR;

        File upFileDirectory = new File(upPath);
        if(!upFileDirectory.exists()){
            upFileDirectory.mkdirs();
        }

        fileDetail uploadedFileDetails = new fileDetail();
        Part part = request.getPart("file");
        String fileName = part.getSubmittedFileName();
        uploadedFileDetails.setFileName(fileName);
        uploadedFileDetails.setFileSize(part.getSize());
        try{
            part.write(upPath + File.separator + fileName);
            uploadedFileDetails.setUploadStatus("Success");
        }
        catch (Exception e){
            uploadedFileDetails.setUploadStatus("Upload Fail: " + e.getMessage());
        }
        request.setAttribute("uploadedFile", uploadedFileDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/uploadFile.jsp");
        dispatcher.forward(request, response);
    }

    public class fileDetail implements Serializable {

        private long fileSize;
        private String fileName, uploadStatus;

        private static final long serialVersionUID = 1L;

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getUploadStatus() {
            return uploadStatus;
        }

        public void setUploadStatus(String uploadStatus) {
            this.uploadStatus = uploadStatus;
        }
    }
}
