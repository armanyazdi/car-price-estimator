package com.armanyazdi.carpriceestimator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Component
public class PriceEstimator {
    private String[] carBrands, carColors;
    private String carGearbox, carStatus;
    private long averagePrice;

    public void averagePrice(String brand, String gearbox, String production, String mileage, String color, String status) {
        ArrayList<String> bamaPricesList = new ArrayList<>();
        ArrayList<String> divarPricesList = new ArrayList<>();
        ArrayList<String> allPricesList = new ArrayList<>();
        long sumBama = 0;
        long sumDivar = 0;
        long sumAll = 0;

        // Cars
        switch (brand) {
            case "پراید 111 EX" -> carBrands = new String[]{"pride-111-ex", "pride/111/ex"};
            case "پراید 111 LX" -> carBrands = new String[]{"pride-111-lx", "pride/111"};
            case "پراید 111 SE" -> carBrands = new String[]{"pride-111-se", "pride/111/se"};
            case "پراید 111 SL" -> carBrands = new String[]{"pride-111-sl", "pride/111/sl"};
            case "پراید 111 SX" -> carBrands = new String[]{"pride-111-sx", "pride/111/sx"};
            case "پراید 131 EX" -> carBrands = new String[]{"pride-131-ex", "pride/131/ex"};
            case "پراید 131 LE" -> carBrands = new String[]{"pride-131-le", "pride/131/le"};
            case "پراید 131 SE" -> carBrands = new String[]{"pride-131-se", "pride/131/se"};
            case "پراید 131 SL" -> carBrands = new String[]{"pride-131-sl", "pride/131/sl"};
            case "پراید 131 SX" -> carBrands = new String[]{"pride-131-sx", "pride/131/sx"};
            case "پراید 131 TL" -> carBrands = new String[]{"pride-131-tl", "pride/131/tl"};
            case "پراید 132 ساده" -> carBrands = new String[]{"pride-132-basic", "pride/132/basic"};
            case "پراید 132 EX" -> carBrands = new String[]{"pride-132-ex", "pride/132/ex"};
            case "پراید 132 LE" -> carBrands = new String[]{"pride-132-le", "pride/132"};
            case "پراید 132 SE" -> carBrands = new String[]{"pride-132-se", "pride/132/se"};
            case "پراید 132 SL" -> carBrands = new String[]{"pride-132-sl", "pride/132/sl"};
            case "پراید 132 SX" -> carBrands = new String[]{"pride-132-sx", "pride/132/sx"};
            case "پراید 141 ساده" -> carBrands = new String[]{"pride-141-basic", "pride/141/basic"};
            case "پراید 141 SE" -> carBrands = new String[]{"pride-141-se", "pride/141/se"};
            case "پراید 141 SL" -> carBrands = new String[]{"pride-141-sl", "pride/141/sl"};
            case "پراید 141 SX" -> carBrands = new String[]{"pride-141-sx", "pride/141/sx"};
            case "پراید 151 پلاس" -> carBrands = new String[]{"pride-151-plus", "pride/pickup/plus"};
            case "پراید 151 SE" -> carBrands = new String[]{"pride-151-se", "pride/pickup/151-se"};
            case "پراید 151 SL" -> carBrands = new String[]{"pride-151-sl", "pride/pickup/151-sl"};
            case "پراید صندوق دار" -> carBrands = new String[]{"pride-sedan", "pride/sedan/petrol"};
            case "پراید هاچ بک" -> carBrands = new String[]{"pride-hatchback", "pride/hatchback"};
            case "پژو 206 تیپ 1" -> carBrands = new String[]{"peugeot-206ir-type1", "peugeot/206/1"};
            case "پژو 206 تیپ 2" -> carBrands = new String[]{"peugeot-206ir-type2", "peugeot/206/2"};
            case "پژو 206 تیپ 3" -> carBrands = new String[]{"peugeot-206ir-type3", "peugeot/206/3"};
            case "پژو 206 تیپ 3 پانوراما" -> carBrands = new String[]{"peugeot-206ir-type3panorama", "peugeot/206/3p"};
            case "پژو 206 تیپ 4" -> carBrands = new String[]{"peugeot-206ir-type4", "peugeot/206/4"};
            case "پژو 206 تیپ 5" -> carBrands = new String[]{"peugeot-206ir-type5", "peugeot/206/5"};
            case "پژو 206 تیپ 6" -> carBrands = new String[]{"peugeot-206ir-type6", "peugeot/206/6"};
            case "پژو 206 صندوقدار V1" -> carBrands = new String[]{"peugeot-206sd-v1", "peugeot/206-sd/v1"};
            case "پژو 206 صندوقدار V10" -> carBrands = new String[]{"peugeot-206sd-v10", "peugeot/206-sd/v10"};
            case "پژو 206 صندوقدار V2" -> carBrands = new String[]{"peugeot-206sd-v2", "peugeot/206-sd/v2"};
            case "پژو 206 صندوقدار V20" -> carBrands = new String[]{"peugeot-206sd-v20", "peugeot/206-sd/v20"};
            case "پژو 206 صندوقدار V6" -> carBrands = new String[]{"peugeot-206sd-v6", "peugeot/206-sd/v6"};
            case "پژو 206 صندوقدار V8" -> carBrands = new String[]{"peugeot-206sd-v8", "peugeot/206-sd/v8"};
            case "پژو 206 صندوقدار V9" -> carBrands = new String[]{"peugeot-206sd-v9", "peugeot/206-sd/v9"};
            case "پژو 207 اتوماتیک TU5" -> carBrands = new String[]{"peugeot-207-at", "peugeot/207i/automatic"};
            case "پژو 207 اتوماتیک TU5P" -> carBrands = new String[]{"peugeot-207-attu5p", "peugeot/207i/automatic"};
            case "پژو 207 پانوراما اتوماتیک TU5" -> carBrands = new String[]{"peugeot-207-automaticpanorama", "peugeot/207i/automatic-p"};
            case "پژو 207 پانوراما اتوماتیک TU5P" -> carBrands = new String[]{"peugeot-207-automaticpanoramatu5p", "peugeot/207i/automatic-p"};
            case "پژو 207 پانوراما دنده ای" -> carBrands = new String[]{"peugeot-207-manualpanorama", "peugeot/207i/manual-p"};
            case "پژو 207 دنده ای" -> carBrands = new String[]{"peugeot-207-mt", "peugeot/207i/manual"};
            case "پژو 207 MC اتوماتیک" -> carBrands = new String[]{"peugeot-207-automaticmc", "peugeot/207i/automatic-mc"};
            case "پژو 207 صندوقدار اتوماتیک" -> carBrands = new String[]{"peugeot-207sd-at", "peugeot/207i-sd/automatic"};
            case "پژو 207 صندوقدار دنده ای" -> carBrands = new String[]{"peugeot-207sd-mt", "peugeot/207i-sd/manual"};
            case "پژو 405 GL" -> carBrands = new String[]{"peugeot-405-gl", "peugeot/405/gl-petrol"};
            case "پژو 405 GLI" -> carBrands = new String[]{"peugeot-405-gli", "peugeot/405/gli-petrol"};
            case "پژو 405 GLX بنزینی" -> carBrands = new String[]{"peugeot-405-glx", "peugeot/405/glx-petrol"};
            case "پژو 405 GLX دوگانه سوز" -> carBrands = new String[]{"peugeot-405-glxcng", "peugeot/405/glx-bi-fuel(cng)"};
            case "پژو 405 SLX" -> carBrands = new String[]{"peugeot-405-slx", "peugeot/405/slx"};
            case "پژو 2008" -> carBrands = new String[]{"peugeot-2008", "peugeot/2008"};
            case "پژو پارس اتوماتیک" -> carBrands = new String[]{"peugeot-pars-at", "peugeot/pars/automatic-tu5"};
            case "پژو پارس دوگانه سوز" -> carBrands = new String[]{"peugeot-pars-cng", "peugeot/pars/bi-fuel"};
            case "پژو پارس ELX-TU5" -> carBrands = new String[]{"peugeot-pars-elxtu5", "peugeot/pars/elx-tu5"};
            case "پژو پارس ELX-XU7" -> carBrands = new String[]{"peugeot-pars-elx", "peugeot/pars/elx"};
            case "پژو پارس ELX-XU7P" -> carBrands = new String[]{"peugeot-pars-elxxu7p", "peugeot/pars/elx"};
            case "پژو پارس ELX-XUM" -> carBrands = new String[]{"peugeot-pars-elxxum", "peugeot/pars/elx-xum"};
            case "پژو پارس LX" -> carBrands = new String[]{"peugeot-pars-lx", "peugeot/pars/lx-tu5"};
            case "پژو پارس XU7" -> carBrands = new String[]{"peugeot-pars-mt", "peugeot/pars/latest"};
            case "پژو پارس XU7P" -> carBrands = new String[]{"peugeot-pars-xu7p", "peugeot/pars/xu7p"};
            case "پژو روآ" -> carBrands = new String[]{"peugeot-roa", "peugeot/roa/petrol"};
            case "پژو RD" -> carBrands = new String[]{"peugeot-rd", "peugeot/rd/petrol"};
            case "پژو RDI" -> carBrands = new String[]{"peugeot-rdi", "peugeot/rdi/petrol"};
            case "تیبا صندوقدار پلاس" -> carBrands = new String[]{"tiba-sedan-plus", "tiba/sedan/plus"};
            case "تیبا صندوقدار EX" -> carBrands = new String[]{"tiba-sedan-ex", "tiba/sedan/ex"};
            case "تیبا صندوقدار SL" -> carBrands = new String[]{"tiba-sedan-sl", "tiba/sedan"};
            case "تیبا صندوقدار SX بنزینی" -> carBrands = new String[]{"tiba-sedan-sx", "tiba/sedan/sx"};
            case "تیبا صندوقدار SX دوگانه سوز" -> carBrands = new String[]{"tiba-sedan-sxcng", "tiba/sedan/sx-bi-fuel"};
            case "تیبا هاچ بک پلاس" -> carBrands = new String[]{"tiba-hatchback-plus", "tiba/hatchback/plus"};
            case "تیبا هاچ بک EX" -> carBrands = new String[]{"tiba-hatchback-ex", "tiba/hatchback/ex"};
            case "تیبا هاچ بک SX" -> carBrands = new String[]{"tiba-hatchback-sx", "tiba/hatchback/sx"};
            case "جک S3 اتوماتیک" -> carBrands = new String[]{"jac-s3-at", "jac/s3/automatic"};
            case "جک S5 اتوماتیک" -> carBrands = new String[]{"jac-s5-at2000", "jac/s5/automatic"};
            case "جک S5 دنده ای" -> carBrands = new String[]{"jac-s5-mt2000", "jac/s5/manual"};
            case "جک S5 نیوفیس" -> carBrands = new String[]{"jac-s5-at1500", "jac/s5-new-face"};
            case "جک جی 3 سدان" -> carBrands = new String[]{"jac-j3sedan", "jac/j3-sedan"};
            case "جک جی 3 هاچ بک" -> carBrands = new String[]{"jac-j3hatchback", "jac/j3-hatchback"};
            case "جک جی 4" -> carBrands = new String[]{"jac-j4", "jac/j4"};
            case "جک جی 5 اتوماتیک" -> carBrands = new String[]{"jac-j5-at", "jac/j5/automatic-1500cc"};
            case "جک جی 5 دنده ای" -> carBrands = new String[]{"jac-j5-mt", "jac/j5/manual-1500cc"};
            case "دانگ فنگ H30 کراس" -> carBrands = new String[]{"dongfeng-h30cross", "dongfeng/h30-cross"};
            case "دنا معمولی" -> carBrands = new String[]{"dena-1.7", "dena/basic"};
            case "دنا پلاس 5 دنده توربو" -> carBrands = new String[]{"dena-plus-turbo", "dena/plus/turbo-2"};
            case "دنا پلاس 6 دنده توربو" -> carBrands = new String[]{"dena-plus-turbo6mt", "dena/plus/manual-6-turbo"};
            case "دنا پلاس اتوماتیک توربو" -> carBrands = new String[]{"dena-plus-turboautomatic", "dena/plus/automatic"};
            case "دنا پلاس دنده ای ساده" -> carBrands = new String[]{"dena-plus-basicmanual", "dena/plus/manual-2"};
            case "رانا پلاس" -> carBrands = new String[]{"runna-plus", "runna/plus"};
            case "رانا پلاس پانوراما" -> carBrands = new String[]{"runna-pluspanorama", "runna/plus-p"};
            case "رانا EL" -> carBrands = new String[]{"runna-el", "runna/el"};
            case "رانا LX" -> carBrands = new String[]{"runna-lx", "runna/lx"};
            case "تارا اتوماتیک" -> carBrands = new String[]{"tara-automatic", "tara/automatic"};
            case "تارا دنده ای" -> carBrands = new String[]{"tara-manual", "tara/manual"};
            case "رنو پارس تندر" -> carBrands = new String[]{"renault-parstondar", "renault/pars-tondar"};
            case "رنو تندر 90 اتوماتیک" -> carBrands = new String[]{"renault-tondar90-at", "renault/tondar-90/automatic"};
            case "رنو تندر 90 پلاس اتوماتیک" -> carBrands = new String[]{"renault-tondar90-plusat", "renault/tondar-90-plus/automatic"};
            case "رنو تندر 90 پلاس دنده ای" -> carBrands = new String[]{"renault-tondar90-plusmt", "renault/tondar-90-plus/manual"};
            case "رنو تندر 90 E0" -> carBrands = new String[]{"renault-tondar90-e0", "renault/tondar-90/e0-petrol"};
            case "رنو تندر 90 E1" -> carBrands = new String[]{"renault-tondar90-e1", "renault/tondar-90/e1-petrol"};
            case "رنو تندر 90 E2" -> carBrands = new String[]{"renault-tondar90-e2", "renault/tondar-90/e2-petrol"};
            case "رنو ساندرو اتوماتیک" -> carBrands = new String[]{"renault-sandero-at", "renault/sandero/automatic"};
            case "رنو ساندرو دنده ای" -> carBrands = new String[]{"renault-sandero-mt", "renault/sandero/manual"};
            case "رنو ساندرو استپ وی اتوماتیک" -> carBrands = new String[]{"renault-sanderostepway-at", "renault/sandero-stepway/automatic"};
            case "رنو ساندرو استپ وی دنده ای" -> carBrands = new String[]{"renault-sanderostepway-mt", "renault/sandero-stepway/manual"};
            case "ساینا اتوماتیک" -> carBrands = new String[]{"saina-at", "saina/automatic"};
            case "ساینا پلاس دنده ای" -> carBrands = new String[]{"saina-manualplus", "saina/manual/plus"};
            case "ساینا EX دنده ای" -> carBrands = new String[]{"saina-exmt", "saina/manual/ex"};
            case "ساینا S دنده ای" -> carBrands = new String[]{"saina-manuals", "saina/manual/s"};
            case "سمند سورن پلاس بنزینی" -> carBrands = new String[]{"samand-soren-plus", "samand/soren-plus"};
            case "سمند سورن ساده" -> carBrands = new String[]{"samand-soren-basic", "samand/soren/basic"};
            case "سمند سورن ELX" -> carBrands = new String[]{"samand-soren-elx", "samand/soren/elx"};
            case "سمند سورن ELX توربو" -> carBrands = new String[]{"samand-soren-elxturbo", "samand/soren/elx-turbo"};
            case "سمند EL" -> carBrands = new String[]{"samand-el", "samand/el/petrol"};
            case "سمند LX EF7" -> carBrands = new String[]{"samand-lx-ef7", "samand/lx/ef7-petrol"};
            case "سمند LX EF7 دوگانه سوز" -> carBrands = new String[]{"samand-lx-ef7cng", "samand/lx/ef7"};
            case "سمند LX XU7" -> carBrands = new String[]{"samand-lx-basic", "samand/lx/basic"};
            case "سمند SE" -> carBrands = new String[]{"samand-se", "samand/se"};
            case "سمند X7" -> carBrands = new String[]{"samand-x7", "samand/x7/petrol"};
            case "سیتروئن زانتیا 1.8" -> carBrands = new String[]{"citroen-xantia-1.8superlux", "citroen/xantia/1800cc"};
            case "سیتروئن زانتیا 2.0 SX" -> carBrands = new String[]{"citroen-xantia-2.0sx", "citroen/xantia/2000cc"};
            case "شاهین G" -> carBrands = new String[]{"shahin-g", "shahin/g"};
            case "کوییک اتوماتیک" -> carBrands = new String[]{"quick-atfull", "quick/automatic/full"};
            case "کوییک اتوماتیک پلاس" -> carBrands = new String[]{"quick-atfullplus", "quick/automatic/full-plus"};
            case "کوییک دنده ای" -> carBrands = new String[]{"quick-manual", "quick/manual/basic"};
            case "کوییک دنده ای R" -> carBrands = new String[]{"quick-manualr", "quick/manual/r"};
            case "کوییک دنده ای S" -> carBrands = new String[]{"quick-manuals", "quick/manual/s"};
            case "کوییک R پلاس اتوماتیک" -> carBrands = new String[]{"quick-manualrplus-at", "quick/automatic/p-plus"};
            case "هایما S5 6 سرعته اتوماتیک" -> carBrands = new String[]{"haima-s5-6at", "haima/s5/6-at"};
            case "هایما S5 گیربکس CVT" -> carBrands = new String[]{"haima-s5-cvt", "haima/s5/at-cvt"};
            case "هایما S7 2.0" -> carBrands = new String[]{"haima-s7-2.0l", "haima/s7/automatic-2000cc"};
            case "هایما S7 1.8 توربو" -> carBrands = new String[]{"haima-s7-1.8lturbo", "haima/s7/automatic-turbo-1800cc"};
            case "هایما S7 1.8 توربو پلاس" -> carBrands = new String[]{"haima-s7-1.8lturboplus", "haima/s7-plus"};
        }

        // Gearboxes
        switch (gearbox) {
            case "اتوماتیک" -> carGearbox = "automatic";
            case "دنده ای" -> carGearbox = "manual";
        }

        // Colors
        switch (color) {
            case "سفید" -> carColors = new String[]{"white", URLEncoder.encode("سفید", StandardCharsets.UTF_8)};
            case "مشکی" -> carColors = new String[]{"black", URLEncoder.encode("مشکی", StandardCharsets.UTF_8)};
            case "خاکستری" -> carColors = new String[]{"gray", URLEncoder.encode("خاکستری", StandardCharsets.UTF_8)};
            case "نقره ای" -> carColors = new String[]{"silver", URLEncoder.encode("نقره‌ای", StandardCharsets.UTF_8)};
            case "سفید صدفی" -> carColors = new String[]{"pearlwhite", URLEncoder.encode("سفید صدفی", StandardCharsets.UTF_8)};
            case "نوک مدادی" -> carColors = new String[]{"blacklead", URLEncoder.encode("نوک‌مدادی", StandardCharsets.UTF_8)};
            case "آبی" -> carColors = new String[]{"blue", URLEncoder.encode("آبی", StandardCharsets.UTF_8)};
            case "قهوه ای" -> carColors = new String[]{"brown", URLEncoder.encode("قهوه‌ای", StandardCharsets.UTF_8)};
            case "قرمز" -> carColors = new String[]{"red", URLEncoder.encode("قرمز", StandardCharsets.UTF_8)};
            case "سرمه ای" -> carColors = new String[]{"darkblue", URLEncoder.encode("سرمه‌ای", StandardCharsets.UTF_8)};
            case "بژ" -> carColors = new String[]{"beige", URLEncoder.encode("بژ", StandardCharsets.UTF_8)};
            case "تیتانیوم" -> carColors = new String[]{"titanium", URLEncoder.encode("تیتانیوم", StandardCharsets.UTF_8)};
            case "سربی" -> carColors = new String[]{"slategray", URLEncoder.encode("سربی", StandardCharsets.UTF_8)};
            case "سبز" -> carColors = new String[]{"green", URLEncoder.encode("سبز", StandardCharsets.UTF_8)};
            case "کربن بلک" -> carColors = new String[]{"carbonblack", URLEncoder.encode("کربن‌بلک", StandardCharsets.UTF_8)};
            case "آلبالویی" -> carColors = new String[]{"maroon" , URLEncoder.encode("آلبالویی", StandardCharsets.UTF_8)};
            case "نقرآبی" -> carColors = new String[]{"steelblue", URLEncoder.encode("نقرآبی", StandardCharsets.UTF_8)};
            case "دلفینی" -> carColors = new String[]{"dolohin", URLEncoder.encode("دلفینی", StandardCharsets.UTF_8)};
            case "زرد" -> carColors = new String[]{"yellow", URLEncoder.encode("زرد", StandardCharsets.UTF_8)};
            case "مسی" -> carColors = new String[]{"copper", URLEncoder.encode("مسی", StandardCharsets.UTF_8)};
            case "یشمی" -> carColors = new String[]{"jadegreen", URLEncoder.encode("یشمی", StandardCharsets.UTF_8)};
            case "بادمجانی" -> carColors = new String[]{"eggplant", URLEncoder.encode("بادمجانی", StandardCharsets.UTF_8)};
            case "نارنجی" -> carColors = new String[]{"orange", URLEncoder.encode("نارنجی", StandardCharsets.UTF_8)};
            case "ذغالی" -> carColors = new String[]{"charcoal", URLEncoder.encode("ذغالی", StandardCharsets.UTF_8)};
            case "طوسی" -> carColors = new String[]{"darkgray", URLEncoder.encode("طوسی", StandardCharsets.UTF_8)};
            case "زیتونی" -> carColors = new String[]{"olivegreen", URLEncoder.encode("زیتونی", StandardCharsets.UTF_8)};
            case "کرم" -> carColors = new String[]{"bisque", URLEncoder.encode("کرم", StandardCharsets.UTF_8)};
            case "گیلاسی" -> carColors = new String[]{"cherry", URLEncoder.encode("گیلاسی", StandardCharsets.UTF_8)};
            case "طلایی" -> carColors = new String[]{"golden", URLEncoder.encode("طلایی", StandardCharsets.UTF_8)};
            case "زرشکی" -> carColors = new String[]{"crimson", URLEncoder.encode("زرشکی", StandardCharsets.UTF_8)};
            case "اطلسی" -> carColors = new String[]{"satin", URLEncoder.encode("اطلسی", StandardCharsets.UTF_8)};
            case "برنز" -> carColors = new String[]{"bronze", URLEncoder.encode("برنز", StandardCharsets.UTF_8)};
            case "عنابی" -> carColors = new String[]{"darkred", URLEncoder.encode("عنابی", StandardCharsets.UTF_8)};
            case "خاکی" -> carColors = new String[]{"khaki", URLEncoder.encode("خاکی", StandardCharsets.UTF_8)};
            case "موکا" -> carColors = new String[]{"mocha", URLEncoder.encode("موکا", StandardCharsets.UTF_8)};
            case "بنفش" -> carColors = new String[]{"purple", URLEncoder.encode("بنفش", StandardCharsets.UTF_8)};
            case "پوست پیازی" -> carColors = new String[]{"onion", URLEncoder.encode("پوست‌پیازی", StandardCharsets.UTF_8)};
            case "یاسی" -> carColors = new String[]{"lilac", URLEncoder.encode("بنفش", StandardCharsets.UTF_8)};
            case "اخرائی" -> carColors = new String[]{"ochre", URLEncoder.encode("نارنجی", StandardCharsets.UTF_8)};
            case "صورتی" -> carColors = new String[]{"pink", URLEncoder.encode("بنفش", StandardCharsets.UTF_8)};
            case "شتری" -> carColors = new String[]{"camellike", URLEncoder.encode("خاکی", StandardCharsets.UTF_8)};
            case "مارون" -> carColors = new String[]{"maroon", URLEncoder.encode("آلبالویی", StandardCharsets.UTF_8)};
        }

        // Statuses
        switch (status) {
            case "بدون رنگ" -> carStatus = "no_paint";
            case "یک لکه رنگ" -> carStatus = "one_paint";
            case "دو لکه رنگ" -> carStatus = "two_paint";
            case "چند لکه رنگ" -> carStatus = "multi_paint";
            case "صافکاری بدون رنگ" -> carStatus = "refinement";
            case "دور رنگ" -> carStatus = "around_paint";
            case "گلگیر رنگ" -> carStatus = "fender_paint";
            case "کاپوت رنگ" -> carStatus = "hood_paint";
            case "یک درب رنگ" -> carStatus = "one_door";
            case "دو درب رنگ" -> carStatus = "two_door";
            case "کامل رنگ" -> carStatus = "full_paint";
            case "کاپوت تعویض" -> carStatus = "hood_replace";
            case "گلگیر تعویض" -> carStatus = "fender_replace";
            case "درب تعویض" -> carStatus = "door_replace";
            case "اتاق تعویض" -> carStatus = "room_replace";
            case "تصادفی" -> carStatus = "crashed";
            case "سوخته" -> carStatus = "burned";
            case "اوراقی" -> carStatus = "scrap";
        }

        // URL
        String urlBama = "https://bama.ir/car/%s-y%s?mileage=%s&priced=1&seller=1&transmission=%s&status=%s&sort=7"
                .formatted(carBrands[0], production, mileage, carGearbox, carStatus);
        String urlDivar = "https://divar.ir/s/iran/car/%s?production-year=%s-%s&usage=%s-%s&business-type=personal&exchange=exclude-exchanges"
                .formatted(carBrands[1], production, production, mileage, (int) (Integer.parseInt(mileage) * 1.5));

        // Data Scraper
        System.setProperty("http.maxRedirects", "999");

        // Bama
        try {
            Connection.Response resBama = Jsoup.connect(urlBama).followRedirects(true).execute();
            Document docBama = Jsoup.parse(resBama.body());
            Elements bamaPrices = docBama.getElementsByClass("bama-ad__price");

            for (Element price : bamaPrices) bamaPricesList.add(price.text().replace(",", ""));
            for (String price : bamaPricesList) sumBama += Long.parseLong(price);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        // Divar
        try {
            Connection.Response resDivar = Jsoup.connect(urlDivar).followRedirects(true).execute();
            Document docDivar = Jsoup.parse(resDivar.body());
            Elements divarPrices = docDivar.getElementsByClass("kt-post-card__description");

            for (Element price : divarPrices)
                if (price.toString().contains("تومان"))
                    divarPricesList.add(persianToEnglish(price.text().replaceAll("[, تومان]", "")));

            for (String price : divarPricesList) sumDivar += Long.parseLong(price);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        // Merge Prices
        allPricesList.addAll(bamaPricesList);
        allPricesList.addAll(divarPricesList);
        for (String price : allPricesList) sumAll += Long.parseLong(price);

        // Final Price
        averagePrice = sumAll / allPricesList.size();
    }

    public long minimumPrice() {
        long minimumPrice = (long) (averagePrice - averagePrice * 0.01);
        return (minimumPrice + 500000) / 1000000 * 1000000;
    }

    public long maximumPrice() {
        long maximumPrice = (long) (averagePrice + averagePrice * 0.01);
        return (maximumPrice + 500000) / 1000000 * 1000000;
    }

    // This method converts Persian/Arabic numbers to English.
    private String persianToEnglish(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }
}
