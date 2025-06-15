package com.quanweng.shopping.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.zxing.WriterException;
import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.mapper.TranslateMapper;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsExcel;
import com.quanweng.shopping.pojo.Translate;
import com.quanweng.shopping.utils.BarcodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class GoodsDataListener extends AnalysisEventListener<GoodsExcel> {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TranslateMapper translateMapper;
    @Override
    public void invoke(GoodsExcel goodsExcel, AnalysisContext context) {
//        Goods goods = new Goods();
//        goods.setGoodsName(goodsExcel.getGoodsName());
//        goods.setGoodsPrice("");
//        goods.setGoodsSize(goodsExcel.getGoodsSize());
//        goods.setGoodsDetail(goodsExcel.getGoodsDetail());
//        goods.setGoodsType("待设置");
//        goods.setGoodsWeight(goodsExcel.getGoodsWeight());
//        goods.setGoodsShowImg("");
//        goods.setGoodsTip(goodsExcel.getGoodsTip());
//        goods.setGoodsTipShow(0);
//        goods.setUpdateTime(LocalDateTime.now());
//        goods.setCreateTime(LocalDateTime.now());
//        goodsMapper.createGoods(goods);
//        try {
//            goods.setGoodsBarCode(BarcodeUtils.generateCode128Barcode(goods.getId().toString()));
//            goodsMapper.addGoodsBarCode(goods);
//        } catch (WriterException | IOException e) {
//            throw new RuntimeException(e);
//        }
//        List<String> textList = new ArrayList<>();
//        textList.add(goodsExcel.getGoodsNameEn());
//        textList.add(goodsExcel.getGoodsDetailEn());
//        textList.add(goodsExcel.getGoodsTipEn());
//        List<String> translateTextList = new ArrayList<>();
//        translateTextList.add(goodsExcel.getGoodsName());
//        translateTextList.add(goodsExcel.getGoodsDetail());
//        translateTextList.add(goodsExcel.getGoodsTip());
//        for (int i = 0;i < 3;i++){
//            Translate translate = new Translate();
//            translate.setText(translateTextList.get(i));
//            translate.setLang("en");
//            translate.setTranslateText(textList.get(i));
//            translate.setUpdateTime(LocalDateTime.now());
//            translate.setCreateTime(LocalDateTime.now());
//            if (translateMapper.getTranslation(translate.getText()).isEmpty()) {
//                translateMapper.createTranslation(translate);
//            }
//        }

        Goods goods = new Goods();
        goods.setGoodsName(goodsExcel.getChineseName());
        goods.setGoodsPrice("");
        goods.setGoodsSize("");
        String[] ProductType = goodsExcel.getProductType().split(" ");
        log.info("{}", Arrays.toString(ProductType));
        log.info("{}",ProductType[1]);
        String detailCn = "<p>成分 : "+goodsExcel.getComposition()+"</p>"+
                "<p>宽 : "+goodsExcel.getWidth()+"</p>"+
                "<p>净重 : "+goodsExcel.getWeight()+"</p>"+
                "<p>产品类型 : "+ProductType[1]+"</p>";
        goods.setGoodsDetail(detailCn);
        goods.setGoodsType("待填");
        goods.setGoodsWeight(1);
        goods.setGoodsShowImg("http://zhaolian.dnwgame.com" +goodsExcel.getImg());
        goods.setGoodsTip("");
        goods.setGoodsTipShow(0);
        goods.setGoodsCode(goodsExcel.getProductCode());
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
//        log.info("{}",goods);
        goodsMapper.createGoods(goods);
        try {
            goods.setGoodsBarCode(BarcodeUtils.generateCode128Barcode(goods.getId().toString()));
            goodsMapper.addGoodsBarCode(goods);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }

        List<String> textList = new ArrayList<>();
        textList.add(goodsExcel.getChineseName());
        textList.add(detailCn);
        textList.add("");
        List<String> translateTextList = new ArrayList<>();
        translateTextList.add(goodsExcel.getEnglishName());
        String detailEn = "<p>composition : "+goodsExcel.getComposition()+"</p>"+
                "<p>width : "+goodsExcel.getWidth()+"</p>"+
                "<p>weight : "+goodsExcel.getWeight()+"</p>"+
                "<p>product type : "+ProductType[0]+"</p>";
        translateTextList.add(detailEn);
        translateTextList.add("");
        for (int i = 0;i < 3;i++){
            Translate translate = new Translate();
            translate.setText(textList.get(i));
            translate.setLang("en");
            translate.setTranslateText(translateTextList.get(i));
            log.info("{},{},{}",textList.get(i),"en",translateTextList.get(i));
            translate.setUpdateTime(LocalDateTime.now());
            translate.setCreateTime(LocalDateTime.now());
            if (translateMapper.getTranslation(translate.getText()).isEmpty()) {
                translateMapper.createTranslation(translate);
            }
        }



    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("Excel读取完成");
    }
}
