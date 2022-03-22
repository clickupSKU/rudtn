package com.example.click_up_final.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.click_up_final.R;
import com.example.click_up_final.WriteActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class HomeFragment extends Fragment {
    private Button btnWrite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        MapView mapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 지도 시작 위치
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.476142313033876, 126.86828278435385), true);

        // 마커 생성 부분
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.566424, 126.978201));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);

        btnWrite = (Button) v.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapView.setMapViewEventListener(new MapView.MapViewEventListener() {
                    @Override
                    public void onMapViewInitialized(MapView mapView) {

                    }

                    @Override
                    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

                    }

                    @Override
                    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

                    }

                    @Override
                    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

                        AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                        ad.setTitle("Click Up");
                        ad.setMessage("게시물을 작성하시겠습니까?" + "\n" + mapPoint.getMapPointGeoCoord().latitude
                                + "\n" + mapPoint.getMapPointGeoCoord().longitude);

                        ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "작성 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), WriteActivity.class);
                                startActivity(intent);
                            }
                        });

                        ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        ad.show();
                    }

                    @Override
                    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

                    }

                    @Override
                    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

                    }

                    @Override
                    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

                    }

                    @Override
                    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

                    }

                    @Override
                    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

                    }
                });


            }
        });


        return v;
    }

}