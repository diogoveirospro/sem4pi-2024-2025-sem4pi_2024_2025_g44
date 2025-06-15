package Shodrone.console.ShowInfo.printer;

import Shodrone.DTO.ShowDTO;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

import java.util.List;
import java.util.Map;

public class ShowPrinter implements Visitor<ShowDTO> {
    @Override
    public void visit(ShowDTO visitee) {
        System.out.printf(
                UtilsUI.BOLD + "Show Description: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Location: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Date: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Time: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Duration: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Quantity of Drones: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Insurance: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Video: " + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "\nFigures Configuration: \n" + UtilsUI.RESET + "%s\n" +
                        UtilsUI.BOLD + "Drone Configuration: " + UtilsUI.RESET + "\n" +
                        "%s\n",
                visitee.showDescription,
                visitee.showLocation,
                visitee.showDate,
                visitee.showTime,
                visitee.showDuration,
                visitee.quantityOfDrones,
                visitee.insurance,
                formatVideo(visitee.video),
                formatList(visitee.figuresConfiguration),
                formatMap(visitee.droneConfiguration)
        );
    }

    private String formatVideo(String video) {
        if (video == null || video.isEmpty()) {
            return "\tNo video available";
        }
        String[] parts = video.split("URL:");
        if (parts.length == 2) {
            return String.format("\t%s\n\tURL: %s", parts[0].trim(), parts[1].trim());
        }
        return "\t" + video;
    }

    private String formatList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        StringBuilder formattedList = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                formattedList.append("- " + list.get(i) + "\n");
            }
        }
        return formattedList.toString();
    }

    private String formatMap(Map<String, List<String>> map) {
        if (map == null || map.isEmpty()) {
            return "[]";
        }
        StringBuilder formattedMap = new StringBuilder();
        map.forEach((key, value) ->
                formattedMap.append(String.format("- %s: %s", key, formatList(value)))
        );
        return formattedMap.toString();
    }
}