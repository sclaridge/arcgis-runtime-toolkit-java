/*
 COPYRIGHT 1995-2019 ESRI

 TRADE SECRETS: ESRI PROPRIETARY AND CONFIDENTIAL
 Unpublished material - all rights reserved under the
 Copyright Laws of the United States.

 For additional information, contact:
 Environmental Systems Research Institute, Inc.
 Attn: Contracts Dept
 380 New York Street
 Redlands, California, USA 92373

 email: contracts@esri.com
 */

package com.esri.arcgisruntime.toolkit;

import java.util.ArrayList;

import com.esri.arcgisruntime.data.ArcGISFeatureTable;
import com.esri.arcgisruntime.data.FeatureTemplate;
import com.esri.arcgisruntime.symbology.Renderer;
import com.esri.arcgisruntime.toolkit.skins.TemplatePickerListSkin;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class TemplatePickerList extends Control {

  private final SimpleStringProperty titleProperty = new SimpleStringProperty();

  private final ObservableList<FeatureTemplate> templateList = FXCollections.observableList(new ArrayList<>());
  private final SimpleListProperty<FeatureTemplate> templateListProperty = new SimpleListProperty<>(templateList);

  private final SimpleObjectProperty<FeatureTemplate> selectedItemProperty = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<Renderer> rendererProperty = new SimpleObjectProperty<>();

  public TemplatePickerList() {
    //this(null, null);
  }

//  public TemplatePickerList(Renderer renderer) {
//    this(null, renderer);
//  }
//
//  public TemplatePickerList(ArcGISFeatureTable featureTable) {
//    this(featureTable, null);
//  }

  public TemplatePickerList(ArcGISFeatureTable featureTable, Renderer renderer) {
    if (featureTable != null) {
      featureTable.addDoneLoadingListener(() -> {
        titleProperty.set(featureTable.getDisplayName());
        templateList.addAll(featureTable.getFeatureTemplates());
        featureTable.getFeatureTypes().forEach(featureType -> templateList.addAll(featureType.getTemplates()));
      });
    }
    rendererProperty.set(renderer);
  }

  public SimpleStringProperty titleProperty() {
    return titleProperty;
  }

  public String getTitle() {
    return titleProperty.get();
  }

  public void setTitle(String title) {
    titleProperty.set(title);
  }

  public SimpleListProperty<FeatureTemplate> templateListProperty() {
    return templateListProperty;
  }

  public void setTemplateList(ObservableList<FeatureTemplate> templateList) {
    templateListProperty.set(templateList);
  }

  public ObservableList<FeatureTemplate> getTemplateList() {
    return templateListProperty.get();
  }

  public SimpleObjectProperty<Renderer> rendererProperty() {
    return rendererProperty;
  }

  public void setRenderer(Renderer renderer) {
    rendererProperty.set(renderer);
  }

  public Renderer getRenderer() {
    return rendererProperty.get();
  }

  public SimpleObjectProperty<FeatureTemplate> selectedItemProperty() {
    return selectedItemProperty;
  }

  public FeatureTemplate getSelecteditem() {
    return selectedItemProperty.get();
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new TemplatePickerListSkin(this);
  }
}