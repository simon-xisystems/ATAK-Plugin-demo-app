package com.atakmap.android.plugintemplate

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import com.atak.plugins.impl.PluginLayoutInflater
import com.atakmap.android.dropdown.DropDown.OnStateListener
import com.atakmap.android.dropdown.DropDownReceiver
import com.atakmap.android.maps.MapView
import com.atakmap.android.plugintemplate.plugin.R
import com.atakmap.android.plugintemplate.plugin.positionMarker
import com.atakmap.coremap.log.Log


class PluginTemplateDropDownReceiver(mapView: MapView?,
                                     private val pluginContext: Context) : DropDownReceiver(mapView), OnStateListener {
    private val templateView: View
    private val templateFragment: View



    /**************************** CONSTRUCTOR  */
    init {

        // Remember to use the PluginLayoutInflator if you are actually inflating a custom view
        // In this case, using it is not necessary - but I am putting it here to remind
        // developers to look at this Inflator
        templateView = PluginLayoutInflater.inflate(pluginContext, R.layout.main_layout, null)
        templateFragment = PluginLayoutInflater.inflate(pluginContext, R.layout.fragment_template, null)


    }

    /**************************** PUBLIC METHODS  */
    public override fun disposeImpl() {}

    /**************************** INHERITED METHODS  */
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        if (action == SHOW_PLUGIN) {
            Log.d(TAG, "showing plugin drop down")
            showDropDown(templateFragment, HALF_WIDTH, FULL_HEIGHT, FULL_WIDTH,
                    HALF_HEIGHT, false)

            //initialise some values to generate a COT
            val plot = positionMarker() //Initialise our Cot plotting Class
            val lat = 37.421998
            var lon = -122.084
            val hae = 0.00      //height above ellipsoid in metres
            val ce = 2.00       //circular error radius in metres
            val le = 2.00       //linear error in metres (height above target)
            val uid = "test uid"
            val friendly = "a-f-G-U-C-I"
            val hostile = "a-h-G-U-C-I"
            var type = ""



            //gain access to the callsign text box on the template fragment
            val callsignTextBox = templateFragment.findViewById(R.id.callsign) as TextView

            val mylat = templateFragment.findViewById(R.id.myLat) as TextView
            val mylong = templateFragment.findViewById(R.id.myLong) as TextView
            val myalt = templateFragment.findViewById(R.id.myAlt) as TextView
            val swHostile = templateFragment.findViewById(R.id.plHostile)
            val swFriend = templateFragment.findViewById(R.id.plFriendly)
            val aleganceText = templateFragment.findViewById(R.id.hostileornot) as TextView



            swHostile.setOnClickListener(){
                plot.drawCotLocal(lat,lon,hae,ce,le,uid,hostile)
                lon++ // Increment the Longitude every time we push the button

            }

            //setup onclick listener to conduct action on press
            swFriend.setOnClickListener(){
                plot.drawCotLocal(lat,lon,hae,ce,le,uid,friendly)
                lon++ // Increment the Longitude every time we push the button
            }




            //get callsign from ATAK.
            val myCallsign = getMapView().deviceCallsign
            val myLat = mapView.selfMarker.point.latitude
            val myLong = mapView.selfMarker.point.longitude
            val myAlt = mapView.selfMarker.point.altitude


            //show call sign in the fragment
            callsignTextBox.setText(myCallsign)
            mylat.setText(myLat.toString())
            mylong.setText(myLong.toString())
            myalt.setText(myAlt.toString() + "  ft MSL")



        }
    }

    override fun onDropDownSelectionRemoved() {}
    override fun onDropDownVisible(v: Boolean) {}
    override fun onDropDownSizeChanged(width: Double, height: Double) {}
    override fun onDropDownClose() {}

    companion object {
        val TAG = PluginTemplateDropDownReceiver::class.java
                .simpleName
        const val SHOW_PLUGIN = "com.atakmap.android.plugintemplate.SHOW_PLUGIN"
    }


}