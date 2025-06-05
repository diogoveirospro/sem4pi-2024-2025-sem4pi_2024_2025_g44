package Shodrone.console.ShowInfo.printer;

import Shodrone.DTO.ShowDTO;
import eapli.framework.visitor.Visitor;

import java.util.List;
import java.util.Map;

public class ShowPrinter implements Visitor<ShowDTO> {
    @Override
    public void visit(ShowDTO visitee) {
        System.out.printf(
                "\nShow Description: %s\nLocation: %s\nDate: %s\nTime: %s\nDuration: %s\n" +
                        "Quantity of Drones: %d\nInsurance: %s\nVideo: %s\n" +
                        "Figures Configuration: %s\nDrone Configuration:\n%s\n",
                visitee.showDescription,
                visitee.showLocation,
                visitee.showDate,
                visitee.showTime,
                visitee.showDuration,
                visitee.quantityOfDrones,
                visitee.insurance,
                visitee.video,
                formatList(visitee.figuresConfiguration),
                formatMap(visitee.droneConfiguration)
        );
    }

    private String formatList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        return list.toString();
    }

    private String formatMap(Map<String, List<String>> map) {
        if (map == null || map.isEmpty()) {
            return "[]";
        }
        StringBuilder formattedMap = new StringBuilder();
        map.forEach((key, value) ->
                formattedMap.append(String.format("- %s: %s%n", key, formatList(value)))
        );
        return formattedMap.toString();
    }
}