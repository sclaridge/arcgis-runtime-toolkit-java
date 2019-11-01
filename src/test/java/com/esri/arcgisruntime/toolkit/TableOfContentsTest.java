package com.esri.arcgisruntime.toolkit;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

public class TableOfContentsTest extends ApplicationTest  {

  /**
   * Tests that a null GeoView argument throws NullPointerException.
   */
  @Test
  public void constructorGeoViewNotNull() {
    Assertions.assertThrows(NullPointerException.class, () -> new TableOfContents(null));
  }

  /**
   * Tests getting the GeoView the TOC is associated with.
   */
  @Test
  public void getGeoView() {
    MapView mapView = new MapView();
    mapView.setMap(new ArcGISMap());
    TableOfContents tableOfContents = new TableOfContents(mapView);
    Assertions.assertEquals(mapView, tableOfContents.getGeoView());
  }
}