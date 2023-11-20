package com.armanyazdi.carpriceestimator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

import static java.net.URLEncoder.encode;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class PriceEstimator {
    private long averagePrice;

    public void averagePrice(String brand, String gearbox, String production, String mileage, String color, String status) {
        ArrayList<String> bamaPricesList = new ArrayList<>();
        ArrayList<String> divarPricesList = new ArrayList<>();
        ArrayList<String> allPricesList = new ArrayList<>();
        long sumBama = 0;
        long sumDivar = 0;
        long sumAll = 0;

        // Cars
        String[] carBrands = switch (brand) {
            case "پراید 111 EX" -> new String[]{"pride-111-ex", "pride/111/ex"};
            case "پراید 111 LX" -> new String[]{"pride-111-lx", "pride/111"};
            case "پراید 111 SE" -> new String[]{"pride-111-se", "pride/111/se"};
            case "پراید 111 SL" -> new String[]{"pride-111-sl", "pride/111/sl"};
            case "پراید 111 SX" -> new String[]{"pride-111-sx", "pride/111/sx"};
            case "پراید 131 EX" -> new String[]{"pride-131-ex", "pride/131/ex"};
            case "پراید 131 LE" -> new String[]{"pride-131-le", "pride/131/le"};
            case "پراید 131 SE" -> new String[]{"pride-131-se", "pride/131/se"};
            case "پراید 131 SL" -> new String[]{"pride-131-sl", "pride/131/sl"};
            case "پراید 131 SX" -> new String[]{"pride-131-sx", "pride/131/sx"};
            case "پراید 131 TL" -> new String[]{"pride-131-tl", "pride/131/tl"};
            case "پراید 132 ساده" -> new String[]{"pride-132-basic", "pride/132/basic"};
            case "پراید 132 EX" -> new String[]{"pride-132-ex", "pride/132/ex"};
            case "پراید 132 LE" -> new String[]{"pride-132-le", "pride/132"};
            case "پراید 132 SE" -> new String[]{"pride-132-se", "pride/132/se"};
            case "پراید 132 SL" -> new String[]{"pride-132-sl", "pride/132/sl"};
            case "پراید 132 SX" -> new String[]{"pride-132-sx", "pride/132/sx"};
            case "پراید 141 ساده" -> new String[]{"pride-141-basic", "pride/141/basic"};
            case "پراید 141 SE" -> new String[]{"pride-141-se", "pride/141/se"};
            case "پراید 141 SL" -> new String[]{"pride-141-sl", "pride/141/sl"};
            case "پراید 141 SX" -> new String[]{"pride-141-sx", "pride/141/sx"};
            case "پراید 151 پلاس" -> new String[]{"pride-151-plus", "pride/pickup/plus"};
            case "پراید 151 SE" -> new String[]{"pride-151-se", "pride/pickup/151-se"};
            case "پراید 151 SL" -> new String[]{"pride-151-sl", "pride/pickup/151-sl"};
            case "پراید صندوق دار" -> new String[]{"pride-sedan", "pride/sedan/petrol"};
            case "پراید هاچ بک" -> new String[]{"pride-hatchback", "pride/hatchback"};
            case "پژو 206 تیپ 1" -> new String[]{"peugeot-206ir-type1", "peugeot/206/1"};
            case "پژو 206 تیپ 2" -> new String[]{"peugeot-206ir-type2", "peugeot/206/2"};
            case "پژو 206 تیپ 3" -> new String[]{"peugeot-206ir-type3", "peugeot/206/3"};
            case "پژو 206 تیپ 3 پانوراما" -> new String[]{"peugeot-206ir-type3panorama", "peugeot/206/3p"};
            case "پژو 206 تیپ 4" -> new String[]{"peugeot-206ir-type4", "peugeot/206/4"};
            case "پژو 206 تیپ 5" -> new String[]{"peugeot-206ir-type5", "peugeot/206/5"};
            case "پژو 206 تیپ 6" -> new String[]{"peugeot-206ir-type6", "peugeot/206/6"};
            case "پژو 206 صندوقدار V1" -> new String[]{"peugeot-206sd-v1", "peugeot/206-sd/v1"};
            case "پژو 206 صندوقدار V10" -> new String[]{"peugeot-206sd-v10", "peugeot/206-sd/v10"};
            case "پژو 206 صندوقدار V2" -> new String[]{"peugeot-206sd-v2", "peugeot/206-sd/v2"};
            case "پژو 206 صندوقدار V20" -> new String[]{"peugeot-206sd-v20", "peugeot/206-sd/v20"};
            case "پژو 206 صندوقدار V6" -> new String[]{"peugeot-206sd-v6", "peugeot/206-sd/v6"};
            case "پژو 206 صندوقدار V8" -> new String[]{"peugeot-206sd-v8", "peugeot/206-sd/v8"};
            case "پژو 206 صندوقدار V9" -> new String[]{"peugeot-206sd-v9", "peugeot/206-sd/v9"};
            case "پژو 207 اتوماتیک TU5" -> new String[]{"peugeot-207-at", "peugeot/207i/automatic"};
            case "پژو 207 اتوماتیک TU5P" -> new String[]{"peugeot-207-attu5p", "peugeot/207i/automatic"};
            case "پژو 207 پانوراما اتوماتیک TU5" -> new String[]{"peugeot-207-automaticpanorama", "peugeot/207i/automatic-p"};
            case "پژو 207 پانوراما اتوماتیک TU5P" -> new String[]{"peugeot-207-automaticpanoramatu5p", "peugeot/207i/automatic-p"};
            case "پژو 207 پانوراما دنده ای" -> new String[]{"peugeot-207-manualpanorama", "peugeot/207i/manual-p"};
            case "پژو 207 تیپ 5" -> new String[]{"peugeot-207-type5", "peugeot/207i/manual"};
            case "پژو 207 دنده ای" -> new String[]{"peugeot-207-mt", "peugeot/207i/manual"};
            case "پژو 207 MC اتوماتیک" -> new String[]{"peugeot-207-automaticmc", "peugeot/207i/automatic-mc"};
            case "پژو 207 TU3" -> new String[]{"peugeot-207-tu3mt", "peugeot/207i/manual"};
            case "پژو 207 صندوقدار اتوماتیک" -> new String[]{"peugeot-207sd-at", "peugeot/207i-sd/automatic"};
            case "پژو 207 صندوقدار دنده ای" -> new String[]{"peugeot-207sd-mt", "peugeot/207i-sd/manual"};
            case "پژو 405 GL" -> new String[]{"peugeot-405-gl", "peugeot/405/gl-petrol"};
            case "پژو 405 GLI" -> new String[]{"peugeot-405-gli", "peugeot/405/gli-petrol"};
            case "پژو 405 GLX بنزینی" -> new String[]{"peugeot-405-glx", "peugeot/405/glx-petrol"};
            case "پژو 405 GLX دوگانه سوز" -> new String[]{"peugeot-405-glxcng", "peugeot/405/glx-bi-fuel(cng)"};
            case "پژو 405 SLX" -> new String[]{"peugeot-405-slx", "peugeot/405/slx"};
            case "پژو 2008" -> new String[]{"peugeot-2008", "peugeot/2008"};
            case "پژو پارس اتوماتیک" -> new String[]{"peugeot-pars-at", "peugeot/pars/automatic-tu5"};
            case "پژو پارس دوگانه سوز" -> new String[]{"peugeot-pars-cng", "peugeot/pars/bi-fuel"};
            case "پژو پارس ELX-TU5" -> new String[]{"peugeot-pars-elxtu5", "peugeot/pars/elx-tu5"};
            case "پژو پارس ELX-XU7" -> new String[]{"peugeot-pars-elx", "peugeot/pars/elx"};
            case "پژو پارس ELX-XU7P" -> new String[]{"peugeot-pars-elxxu7p", "peugeot/pars/elx"};
            case "پژو پارس ELX-XUM" -> new String[]{"peugeot-pars-elxxum", "peugeot/pars/elx-xum"};
            case "پژو پارس LX" -> new String[]{"peugeot-pars-lx", "peugeot/pars/lx-tu5"};
            case "پژو پارس XU7" -> new String[]{"peugeot-pars-mt", "peugeot/pars/latest"};
            case "پژو پارس XU7P" -> new String[]{"peugeot-pars-xu7p", "peugeot/pars/xu7p"};
            case "پژو روآ" -> new String[]{"peugeot-roa", "peugeot/roa/petrol"};
            case "پژو RD" -> new String[]{"peugeot-rd", "peugeot/rd/petrol"};
            case "پژو RDI" -> new String[]{"peugeot-rdi", "peugeot/rdi/petrol"};
            case "پیکان بنزینی" -> new String[]{"peykan-sedan-gas", "paykan/petrol"};
            case "پیکان دوگانه سوز" -> new String[]{"peykan-sedan-cng", "paykan/bi-fuel(cng)"};
            case "پیکان وانت بنزینی" -> new String[]{"peykan-pickup-gas", "paykan/pickup/petrol"};
            case "پیکان وانت دوگانه سوز" -> new String[]{"peykan-pickup-cng", "paykan/pickup/cng"};
            case "تیبا صندوقدار پلاس" -> new String[]{"tiba-sedan-plus", "tiba/sedan/plus"};
            case "تیبا صندوقدار EX" -> new String[]{"tiba-sedan-ex", "tiba/sedan/ex"};
            case "تیبا صندوقدار SL" -> new String[]{"tiba-sedan-sl", "tiba/sedan"};
            case "تیبا صندوقدار SX بنزینی" -> new String[]{"tiba-sedan-sx", "tiba/sedan/sx"};
            case "تیبا صندوقدار SX دوگانه سوز" -> new String[]{"tiba-sedan-sxcng", "tiba/sedan/sx-bi-fuel"};
            case "تیبا هاچ بک پلاس" -> new String[]{"tiba-hatchback-plus", "tiba/hatchback/plus"};
            case "تیبا هاچ بک EX" -> new String[]{"tiba-hatchback-ex", "tiba/hatchback/ex"};
            case "تیبا هاچ بک SX" -> new String[]{"tiba-hatchback-sx", "tiba/hatchback/sx"};
            case "جک S3 اتوماتیک" -> new String[]{"jac-s3-at", "jac/s3/automatic"};
            case "جک S5 اتوماتیک" -> new String[]{"jac-s5-at2000", "jac/s5/automatic"};
            case "جک S5 دنده ای" -> new String[]{"jac-s5-mt2000", "jac/s5/manual"};
            case "جک S5 نیوفیس" -> new String[]{"jac-s5-at1500", "jac/s5-new-face"};
            case "جک جی 3 سدان" -> new String[]{"jac-j3sedan", "jac/j3-sedan"};
            case "جک جی 3 هاچ بک" -> new String[]{"jac-j3hatchback", "jac/j3-hatchback"};
            case "جک جی 4" -> new String[]{"jac-j4", "jac/j4"};
            case "جک جی 5 اتوماتیک" -> new String[]{"jac-j5-at", "jac/j5/automatic-1500cc"};
            case "جک جی 5 دنده ای" -> new String[]{"jac-j5-mt", "jac/j5/manual-1500cc"};
            case "دانگ فنگ H30 کراس" -> new String[]{"dongfeng-h30cross", "dongfeng/h30-cross"};
            case "دنا معمولی" -> new String[]{"dena-1.7", "dena/basic"};
            case "دنا پلاس 5 دنده توربو" -> new String[]{"dena-plus-turbo", "dena/plus/turbo-2"};
            case "دنا پلاس 6 دنده توربو" -> new String[]{"dena-plus-turbo6mt", "dena/plus/manual-6-turbo"};
            case "دنا پلاس اتوماتیک توربو" -> new String[]{"dena-plus-turboautomatic", "dena/plus/automatic"};
            case "دنا پلاس دنده ای ساده" -> new String[]{"dena-plus-basicmanual", "dena/plus/manual-2"};
            case "دیگنیتی پرایم" -> new String[]{"dignity-prime", "dignity"};
            case "دیگنیتی پرستیژ" -> new String[]{"dignity-prestige", "dignity"};
            case "رانا پلاس" -> new String[]{"runna-plus", "runna/plus"};
            case "رانا پلاس پانوراما" -> new String[]{"runna-pluspanorama", "runna/plus-p"};
            case "رانا EL" -> new String[]{"runna-el", "runna/el"};
            case "رانا LX" -> new String[]{"runna-lx", "runna/lx"};
            case "تارا اتوماتیک" -> new String[]{"tara-automatic", "tara/automatic"};
            case "تارا دنده ای" -> new String[]{"tara-manual", "tara/manual"};
            case "رنو پارس تندر" -> new String[]{"renault-parstondar", "renault/pars-tondar"};
            case "رنو تندر 90 اتوماتیک" -> new String[]{"renault-tondar90-at", "renault/tondar-90/automatic"};
            case "رنو تندر 90 پلاس اتوماتیک" -> new String[]{"renault-tondar90-plusat", "renault/tondar-90-plus/automatic"};
            case "رنو تندر 90 پلاس دنده ای" -> new String[]{"renault-tondar90-plusmt", "renault/tondar-90-plus/manual"};
            case "رنو تندر 90 E0" -> new String[]{"renault-tondar90-e0", "renault/tondar-90/e0-petrol"};
            case "رنو تندر 90 E1" -> new String[]{"renault-tondar90-e1", "renault/tondar-90/e1-petrol"};
            case "رنو تندر 90 E2" -> new String[]{"renault-tondar90-e2", "renault/tondar-90/e2-petrol"};
            case "رنو ساندرو اتوماتیک" -> new String[]{"renault-sandero-at", "renault/sandero/automatic"};
            case "رنو ساندرو دنده ای" -> new String[]{"renault-sandero-mt", "renault/sandero/manual"};
            case "رنو ساندرو استپ وی اتوماتیک" -> new String[]{"renault-sanderostepway-at", "renault/sandero-stepway/automatic"};
            case "رنو ساندرو استپ وی دنده ای" -> new String[]{"renault-sanderostepway-mt", "renault/sandero-stepway/manual"};
            case "ریسپکت پرایم" -> new String[]{"respect", "respect/prime"};
            case "ساینا اتوماتیک" -> new String[]{"saina-at", "saina/automatic"};
            case "ساینا پلاس دنده ای" -> new String[]{"saina-manualplus", "saina/manual/plus"};
            case "ساینا EX دنده ای" -> new String[]{"saina-exmt", "saina/manual/ex"};
            case "ساینا S دنده ای" -> new String[]{"saina-manuals", "saina/manual/s"};
            case "سمند سورن پلاس بنزینی" -> new String[]{"samand-soren-plus", "samand/soren-plus"};
            case "سمند سورن ساده" -> new String[]{"samand-soren-basic", "samand/soren/basic"};
            case "سمند سورن ELX" -> new String[]{"samand-soren-elx", "samand/soren/elx"};
            case "سمند سورن ELX توربو" -> new String[]{"samand-soren-elxturbo", "samand/soren/elx-turbo"};
            case "سمند EL" -> new String[]{"samand-el", "samand/el/petrol"};
            case "سمند LX EF7" -> new String[]{"samand-lx-ef7", "samand/lx/ef7-petrol"};
            case "سمند LX EF7 دوگانه سوز" -> new String[]{"samand-lx-ef7cng", "samand/lx/ef7"};
            case "سمند LX XU7" -> new String[]{"samand-lx-basic", "samand/lx/basic"};
            case "سمند SE" -> new String[]{"samand-se", "samand/se"};
            case "سمند X7" -> new String[]{"samand-x7", "samand/x7/petrol"};
            case "سیتروئن زانتیا 1.8" -> new String[]{"citroen-xantia-1.8superlux", "citroen/xantia/1800cc"};
            case "سیتروئن زانتیا 2.0 SX" -> new String[]{"citroen-xantia-2.0sx", "citroen/xantia/2000cc"};
            case "شاهین G" -> new String[]{"shahin-g", "shahin/g"};
            case "فردا 511" -> new String[]{"farda-511", "farda/511"};
            case "فردا SX5" -> new String[]{"farda-sx5", "farda/sx5"};
            case "فردا SX6" -> new String[]{"farda-sx6", "farda/sx6"};
            case "فردا T5" -> new String[]{"farda-t5", "farda/t5"};
            case "فیدلیتی پرایم 5 نفره" -> new String[]{"fidelity-prime-5seater", "fidelity/prime/5seater"};
            case "فیدلیتی پرایم 7 نفره" -> new String[]{"fidelity-prime-7seater", "fidelity/prime/7seater"};
            case "کوییک اتوماتیک" -> new String[]{"quick-atfull", "quick/automatic/full"};
            case "کوییک اتوماتیک پلاس" -> new String[]{"quick-atfullplus", "quick/automatic/full-plus"};
            case "کوییک دنده ای" -> new String[]{"quick-manual", "quick/manual/basic"};
            case "کوییک دنده ای R" -> new String[]{"quick-manualr", "quick/manual/r"};
            case "کوییک دنده ای S" -> new String[]{"quick-manuals", "quick/manual/s"};
            case "کوییک R پلاس اتوماتیک" -> new String[]{"quick-manualrplus-at", "quick/automatic/p-plus"};
            case "کی ام سی J7" -> new String[]{"kmc-j7", "kmc/j7"};
            case "کی ام سی K7" -> new String[]{"kmc-k7", "kmc/k7"};
            case "کی ام سی T8" -> new String[]{"kmc-t8", "kmc/t8"};
            case "لاماری ایما" -> new String[]{"lamari", "lamari/eama"};
            case "هایما S5 6 سرعته اتوماتیک" -> new String[]{"haima-s5-6at", "haima/s5/6-at"};
            case "هایما S5 گیربکس CVT" -> new String[]{"haima-s5-cvt", "haima/s5/at-cvt"};
            case "هایما S7 2.0" -> new String[]{"haima-s7-2.0l", "haima/s7/automatic-2000cc"};
            case "هایما S7 1.8 توربو" -> new String[]{"haima-s7-1.8lturbo", "haima/s7/automatic-turbo-1800cc"};
            case "هایما S7 1.8 توربو پلاس" -> new String[]{"haima-s7-1.8lturboplus", "haima/s7-plus"};
            default -> throw new IllegalStateException("Unexpected value: " + brand);
        };

        // Gearboxes
        String carGearbox = switch (gearbox) {
            case "اتوماتیک" -> "automatic";
            case "دنده ای" -> "manual";
            default -> throw new IllegalStateException("Unexpected value: " + gearbox);
        };

        // Colors
        String[] carColors = switch (color) {
            case "سفید" -> new String[]{"white", encode("سفید", UTF_8)};
            case "مشکی" -> new String[]{"black", encode("مشکی", UTF_8)};
            case "خاکستری" -> new String[]{"gray", encode("خاکستری", UTF_8)};
            case "نقره ای" -> new String[]{"silver", encode("نقره‌ای", UTF_8)};
            case "سفید صدفی" -> new String[]{"pearlwhite", encode("سفید صدفی", UTF_8)};
            case "نوک مدادی" -> new String[]{"blacklead", encode("نوک‌مدادی", UTF_8)};
            case "آبی" -> new String[]{"blue", encode("آبی", UTF_8)};
            case "قهوه ای" -> new String[]{"brown", encode("قهوه‌ای", UTF_8)};
            case "قرمز" -> new String[]{"red", encode("قرمز", UTF_8)};
            case "سرمه ای" -> new String[]{"darkblue", encode("سرمه‌ای", UTF_8)};
            case "بژ" -> new String[]{"beige", encode("بژ", UTF_8)};
            case "تیتانیوم" -> new String[]{"titanium", encode("تیتانیوم", UTF_8)};
            case "سربی" -> new String[]{"slategray", encode("سربی", UTF_8)};
            case "سبز" -> new String[]{"green", encode("سبز", UTF_8)};
            case "کربن بلک" -> new String[]{"carbonblack", encode("کربن‌بلک", UTF_8)};
            case "آلبالویی" -> new String[]{"maroon", encode("آلبالویی", UTF_8)};
            case "نقرآبی" -> new String[]{"steelblue", encode("نقرآبی", UTF_8)};
            case "دلفینی" -> new String[]{"dolohin", encode("دلفینی", UTF_8)};
            case "زرد" -> new String[]{"yellow", encode("زرد", UTF_8)};
            case "مسی" -> new String[]{"copper", encode("مسی", UTF_8)};
            case "یشمی" -> new String[]{"jadegreen", encode("یشمی", UTF_8)};
            case "بادمجانی" -> new String[]{"eggplant", encode("بادمجانی", UTF_8)};
            case "نارنجی" -> new String[]{"orange", encode("نارنجی", UTF_8)};
            case "ذغالی" -> new String[]{"charcoal", encode("ذغالی", UTF_8)};
            case "طوسی" -> new String[]{"darkgray", encode("طوسی", UTF_8)};
            case "زیتونی" -> new String[]{"olivegreen", encode("زیتونی", UTF_8)};
            case "کرم" -> new String[]{"bisque", encode("کرم", UTF_8)};
            case "گیلاسی" -> new String[]{"cherry", encode("گیلاسی", UTF_8)};
            case "طلایی" -> new String[]{"golden", encode("طلایی", UTF_8)};
            case "زرشکی" -> new String[]{"crimson", encode("زرشکی", UTF_8)};
            case "اطلسی" -> new String[]{"satin", encode("اطلسی", UTF_8)};
            case "برنز" -> new String[]{"bronze", encode("برنز", UTF_8)};
            case "عنابی" -> new String[]{"darkred", encode("عنابی", UTF_8)};
            case "خاکی" -> new String[]{"khaki", encode("خاکی", UTF_8)};
            case "موکا" -> new String[]{"mocha", encode("موکا", UTF_8)};
            case "بنفش" -> new String[]{"purple", encode("بنفش", UTF_8)};
            case "پوست پیازی" -> new String[]{"onion", encode("پوست‌پیازی", UTF_8)};
            case "یاسی" -> new String[]{"lilac", encode("بنفش", UTF_8)};
            case "اخرائی" -> new String[]{"ochre", encode("نارنجی", UTF_8)};
            case "صورتی" -> new String[]{"pink", encode("بنفش", UTF_8)};
            case "عدسی" -> new String[]{"lentil", encode("عدسی", UTF_8)};
            case "مارون" -> new String[]{"maroon", encode("آلبالویی", UTF_8)};
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };

        // Statuses
        String carStatus = switch (status) {
            case "بدون رنگ" -> "no_paint";
            case "یک لکه رنگ" -> "one_paint";
            case "دو لکه رنگ" -> "two_paint";
            case "چند لکه رنگ" -> "multi_paint";
            case "صافکاری بدون رنگ" -> "refinement";
            case "دور رنگ" -> "around_paint";
            case "گلگیر رنگ" -> "fender_paint";
            case "کاپوت رنگ" -> "hood_paint";
            case "یک درب رنگ" -> "one_door";
            case "دو درب رنگ" -> "two_door";
            case "کامل رنگ" -> "full_paint";
            case "کاپوت تعویض" -> "hood_replace";
            case "گلگیر تعویض" -> "fender_replace";
            case "درب تعویض" -> "door_replace";
            case "اتاق تعویض" -> "room_replace";
            case "تصادفی" -> "crashed";
            case "سوخته" -> "burned";
            case "اوراقی" -> "scrap";
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };

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
        catch (IOException e) {
            e.printStackTrace();
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
        catch (IOException e) {
            e.printStackTrace();
        }

        // Merge Prices
        allPricesList.addAll(bamaPricesList);
        allPricesList.addAll(divarPricesList);
        for (String price : allPricesList) sumAll += Long.parseLong(price);

        // Final Price
        if (allPricesList.size() != 0)
            averagePrice = sumAll / allPricesList.size();
        else
            averagePrice = 0;
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
