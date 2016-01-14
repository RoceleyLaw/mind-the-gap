package ca.ubc.cs.cpsc210.mindthegap.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import ca.ubc.cs.cpsc210.mindthegap.R;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.util.LatLon;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.*;

/**
 * StationInfoWindow displayed when station is tapped
 */
class StationInfoWindow extends MarkerInfoWindow {
    private StationSelectionListener stnSelectionListener;


    //modified
   // private Set<Line> lines1;  // = new HashSet<Line>();
    //private Set<Line> lines2;
    //private LatLon point1;
    //private LatLon point2;
    //private Polyline points;
    private Station oldStation;
    //private Polyline point2;

    /**
     * Constructor
     *
     * @param listener   listener to handle user selection of station
     * @param mapView    the map view on which this info window will be displayed
     */
    public StationInfoWindow(StationSelectionListener listener, MapView mapView) {
        super(R.layout.bonuspack_bubble, mapView);
         stnSelectionListener = listener;
        //points = new Polyline(mapView.getContext());

        Button btn = (Button) (mView.findViewById(R.id.bubble_moreinfo));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Modified


                //

                Station selected = (Station) mMarkerRef.getRelatedObject();
                stnSelectionListener.onStationSelected(selected);

                //Modified
         //       if(lines1.size() != 0){
         //           lines2 = selected.getLines();
         //           point2 = selected.getLocn();

           //     }else {
             //       lines1 = selected.getLines();
             //       point1 = selected.getLocn();
             //   }

               // for(Line line1:lines1){

           //         for(Line line2:lines2){
             //           List<GeoPoint> geoPoints = new ArrayList<GeoPoint>();
               //         if(line2.equals(line1)) {
                 //           GeoPoint geoPoint1 = new GeoPoint(point1.getLatitude(),point1.getLongitude());
                   //         GeoPoint geoPoint2 = new GeoPoint(point2.getLatitude(),point2.getLongitude());
//
  //                          geoPoints.add(geoPoint1);
    //                        geoPoints.add(geoPoint2);
//
//
//
  //                          points.setPoints(geoPoints);
    //                        points.setColor(Color.YELLOW);
      //                      points.setWidth(50);
        //                }
          //          }
                //}




                //

                //modified condition
              //  if(lines2.size() != 0)
                StationInfoWindow.this.close();
            }
        });
    }

    @Override public void onOpen(Object item){
        Marker marker = (Marker)item;
        Station newStation = (Station) marker.getRelatedObject();
        if (newStation.equals(oldStation)) {
            oldStation = newStation;
            super.onOpen(item);
            mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);

            return;
        }

        if (oldStation != null) {
            for (Line line : oldStation.getLines()) {
                if (newStation.getLines().contains(line)) {
                    marker.setTitle(newStation.getName() +  "\n(" + oldStation.getName()+" is on the same line\n" +"with it)");
                    oldStation = newStation;
                    super.onOpen(item);
                    mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);
                    marker.setTitle(newStation.getName());
                    return;
                }
            }
            marker.setTitle(newStation.getName() +"\n(" + oldStation.getName()+ " is not on the same line"+" with it"+")");
            oldStation = newStation;
            super.onOpen(item);
            mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);
            marker.setTitle(newStation.getName());
            return;
        }

        oldStation = newStation;
        super.onOpen(item);
        mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);
    }




      //original ones
       // super.onOpen(item);
        // mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);



}
