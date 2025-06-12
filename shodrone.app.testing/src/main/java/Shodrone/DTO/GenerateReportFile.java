package Shodrone.DTO;

public class GenerateReportFile {
    private String reportFileName;
    private String reportFileContent;

    public GenerateReportFile(String reportFileName, String reportFileContent) {
        this.reportFileName = reportFileName;
        this.reportFileContent = reportFileContent;
    }

    public String fileName() {
        return reportFileName;
    }

    public String fileContent() {
        return reportFileContent;
    }


}
