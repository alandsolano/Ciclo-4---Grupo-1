package com.example.delcampofresco;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Hashtable;
import java.util.Set;

public class DB extends Fragment {
    View fragment, product, close;
    TextView productDetail, originDetail, nutritionDetail, questionsDetail;

    public DB(Context ct, View root, String data) {
        Hashtable<Integer, String[]> products = new Hashtable<Integer, String[]>();
        if (data == "fruits") {
            products.put(R.id.fresa, new String[] {ct.getString(R.string.fresa), ct.getString(R.string.fresaOrigin), ct.getString(R.string.fresaNutrition), ct.getString(R.string.fresaQuestions)});
            products.put(R.id.mandarina, new String[] {ct.getString(R.string.mandarina), ct.getString(R.string.mandarinaOrigin), ct.getString(R.string.mandarinaNutrition), ct.getString(R.string.mandarinaQuestions)});
            products.put(R.id.guayaba, new String[] {ct.getString(R.string.guayaba), ct.getString(R.string.guayabaOrigin), ct.getString(R.string.guayabaNutrition), ct.getString(R.string.guayabaQuestions)});
            products.put(R.id.naranja, new String[] {ct.getString(R.string.naranja), ct.getString(R.string.naranjaOrigin), ct.getString(R.string.naranjaNutrition), ct.getString(R.string.naranjaQuestions)});
            products.put(R.id.manzana, new String[] {ct.getString(R.string.manzana), ct.getString(R.string.manzanaOrigin), ct.getString(R.string.manzanaNutrition), ct.getString(R.string.manzanaQuestions)});
            products.put(R.id.arandano, new String[] {ct.getString(R.string.arandano), ct.getString(R.string.arandanoOrigin), ct.getString(R.string.arandanoNutrition), ct.getString(R.string.arandanoQuestions)});
            products.put(R.id.banano, new String[] {ct.getString(R.string.banano), ct.getString(R.string.bananoOrigin), ct.getString(R.string.bananoNutrition), ct.getString(R.string.bananoQuestions)});
            products.put(R.id.papaya, new String[] {ct.getString(R.string.papaya), ct.getString(R.string.papayaOrigin), ct.getString(R.string.papayaNutrition), ct.getString(R.string.papayaQuestions)});
        }
        if (data == "vegetables") {
            products.put(R.id.aguacate, new String[] {ct.getString(R.string.aguacate), ct.getString(R.string.aguacateOrigin), ct.getString(R.string.aguacateNutrition), ct.getString(R.string.aguacateQuestions)});
            products.put(R.id.tomate, new String[] {ct.getString(R.string.tomate), ct.getString(R.string.tomateOrigin), ct.getString(R.string.tomateNutrition), ct.getString(R.string.tomateQuestions)});
            products.put(R.id.pimenton, new String[] {ct.getString(R.string.pimenton), ct.getString(R.string.pimentonOrigin), ct.getString(R.string.pimentonNutrition), ct.getString(R.string.pimentonQuestions)});
            products.put(R.id.lechuga, new String[] {ct.getString(R.string.lechuga), ct.getString(R.string.lechugaOrigin), ct.getString(R.string.lechugaNutrition), ct.getString(R.string.lechugaQuestions)});
            products.put(R.id.limon, new String[] {ct.getString(R.string.limon), ct.getString(R.string.limonOrigin), ct.getString(R.string.limonNutrition), ct.getString(R.string.limonQuestions)});
            products.put(R.id.brocoli, new String[] {ct.getString(R.string.brocoli), ct.getString(R.string.brocoliOrigin), ct.getString(R.string.brocoliNutrition), ct.getString(R.string.brocoliQuestions)});
        }
        Set<Integer> productsKeys = products.keySet();

        fragment = root.findViewById(R.id.fragmentDetail);
        fragment.setVisibility(View.INVISIBLE);
        productDetail = root.findViewById(R.id.productDetail);
        originDetail = root.findViewById(R.id.originDetail);
        nutritionDetail = root.findViewById(R.id.nutritionDetail);
        questionsDetail = root.findViewById(R.id.questionsDetail);
        close = root.findViewById(R.id.closeDetail);

        for (Integer key : productsKeys) {
            product = root.findViewById(key);
            product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetail.setText(products.get(key)[0]);
                    originDetail.setText(products.get(key)[1]);
                    nutritionDetail.setText(products.get(key)[2]);
                    questionsDetail.setText(products.get(key)[3]);
                    fragment.setVisibility(View.VISIBLE);
                }
            });
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.setVisibility(View.INVISIBLE);
            }
        });
    }

}
