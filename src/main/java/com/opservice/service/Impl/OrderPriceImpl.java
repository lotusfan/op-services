package com.opservice.service.Impl;

import com.opservice.service.SServiceServiceIn;
import com.opservice.service.VehicleServiceIn;
import com.yellowcar.api.op.OrderPriceIn;
import com.yellowcar.entities.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zhangfan on 2014/12/22.
 */
@Service
public class OrderPriceImpl implements OrderPriceIn {

    @Autowired
    private SServiceServiceIn sServiceServiceIn;

    @Autowired
    private VehicleServiceIn vehicleServiceIn;

    private DateFormat df = DateFormat.getDateInstance();
    private String TYPE = "type";
    private String PRIMEPRICE = "primePrice";
    private String SELLPRICE = "sellPrice";
    private String MONEYTYPE = "moneyType";
    private String UNIT = "unit";


    @Override
    public String getVehiclePrice(String vehiclePackageId, String supplierId, String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(df.parse(date));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(TYPE, "product");

            VehicleSupplierPriceCalendar vspc = new VehicleSupplierPriceCalendar();
            vspc.setVehiclePackageId(Long.parseLong(vehiclePackageId));
            vspc.setSupplierId(Long.parseLong(supplierId));
            vspc.setYear(calendar.get(Calendar.YEAR));
            vspc.setMonth(calendar.get(Calendar.MONTH)+1);
            vspc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            List<VehicleSupplierPriceCalendar> listvspcs = vehicleServiceIn.getVehicleSPCBy(vspc);
            jsonObject.put(PRIMEPRICE, listvspcs.get(0).getPrice());
            jsonObject.put(MONEYTYPE, listvspcs.get(0).getMoneyType());

            VehiclePriceCalendar vpc = new VehiclePriceCalendar();
            vpc.setVehiclePackageId(Long.parseLong(vehiclePackageId));
            vpc.setYear(calendar.get(Calendar.YEAR));
            vpc.setMonth(calendar.get(Calendar.MONTH )+1);
            vpc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            List<VehiclePriceCalendar> listvpcs = vehicleServiceIn.getVPCBy(vpc);
            jsonObject.put(SELLPRICE, listvpcs.get(0).getPrice());

            VehiclePackage vp = vehicleServiceIn.getVehiclePackage(Long.parseLong(vehiclePackageId));
            jsonObject.put(UNIT, vp.getUnit());
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getServicePackagePrice(String servicePackageId, String supplierId, String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(df.parse(date));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(TYPE, "service");

            ServicePackageSPriceCalendar spspc = new ServicePackageSPriceCalendar();
            spspc.setServicePackageId(Long.parseLong(servicePackageId));
            spspc.setSupplierId(Long.parseLong(supplierId));
            spspc.setYear(calendar.get(Calendar.YEAR));
            spspc.setMonth(calendar.get(Calendar.MONTH)+1);
            spspc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            List<ServicePackageSPriceCalendar> listspspcs = sServiceServiceIn.getSPSPCBy(spspc);
            jsonObject.put(PRIMEPRICE, listspspcs.get(0).getPrice());
            jsonObject.put(MONEYTYPE, listspspcs.get(0).getMoneyType());

            ServicePackagePriceCalendar sppc = new ServicePackagePriceCalendar();
            sppc.setServicePackageId(Long.parseLong(servicePackageId));
            sppc.setYear(calendar.get(Calendar.YEAR));
            sppc.setMonth(calendar.get(Calendar.MONTH)+1);
            sppc.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            List<ServicePackagePriceCalendar> listsppcs = sServiceServiceIn.getSPPCBy(sppc);
            jsonObject.put(SELLPRICE, listsppcs.get(0).getPrice());

            ServicePackage sp = sServiceServiceIn.getServicePackageById(Long.parseLong(servicePackageId));
            jsonObject.put(UNIT, sp.getUnit());
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
