package com.covidstats.rest;

import com.covidstats.controller.RecordController;
import com.covidstats.model.Record;
import com.covidstats.utils.GsonUtil;
import com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("records")
public class RecordService {

    @GET
    @Path("/{input}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecordByContinent(@PathParam("input") String input) {

        String[] inputElements = input.split(",");
        List<Record> recordList = RecordController.getInstance().index();

        List<Record> records = new ArrayList<>();

        recordList.stream()
                .filter(record -> {
                    for (String s : inputElements) {
                        if (record.getCountry().getIsoCode().equalsIgnoreCase(s)
                                || record.getCountry().getContinent().equalsIgnoreCase(s)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.groupingBy(record -> record.getCountry().getContinent(),
                        Collectors.collectingAndThen(
                                Collectors.toList(), list -> {
                                    list.stream().collect(Collectors.groupingBy(record -> record.getCountry().getLocation()))
                                            .values().forEach(recordsForLoc -> {
                                                Record r = new Record();

                                                r.setCountry(recordsForLoc.stream().findFirst().get().getCountry());
                                                r.setTotalCases(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getTotalCases)));
                                                r.setNewCases(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getNewCases)));
                                                r.setNewCasesSmoothed(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getNewCasesSmoothed)));
                                                r.setTotalDeaths(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getTotalDeaths)));
                                                r.setNewDeaths(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getNewDeaths)));
                                                r.setNewDeathsSmoothed(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getNewDeathsSmoothed)));
                                                r.setReproductionRate(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getReproductionRate)));
                                                r.setNewTests(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getNewTests)));
                                                r.setTotalTests(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getTotalTests)));
                                                r.setStringencyIndex(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getStringencyIndex)));
                                                r.setPopulation(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getPopulation)));
                                                r.setMedianAge(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getMedianAge)));
                                                r.setNewDeathsPerCase(recordsForLoc.stream().collect(Collectors.averagingDouble(Record::getNewDeathsPerCase)));
                                                records.add(r);

                                            });
                                    return null;
                                })));

        return Response.status(Response.Status.OK)
                .entity(getJsonObjectWithTotalRecords(records))
                .build();
    }

    private JsonObject getJsonObjectWithTotalRecords(List<Record> records) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.add("records", GsonUtil.getRecordSerializerGson().toJsonTree(records));
        return jsonObject;
    }
}
