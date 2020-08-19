package com.atakmap.android.plugintemplate

import android.content.Context
import android.content.Intent
import com.atakmap.android.cot.CotMapComponent
import com.atakmap.android.dropdown.DropDownMapComponent
import com.atakmap.android.ipc.AtakBroadcast.DocumentedIntentFilter
import com.atakmap.android.maps.MapView
import com.atakmap.android.plugintemplate.plugin.R
import com.atakmap.coremap.cot.event.CotEvent
import com.atakmap.coremap.cot.event.CotPoint
import com.atakmap.coremap.log.Log
import com.atakmap.coremap.maps.time.CoordinatedTime

class PluginTemplateMapComponent : DropDownMapComponent() {
    private var pluginContext: Context? = null
    private var ddr: PluginTemplateDropDownReceiver? = null
    override fun onCreate(context: Context, intent: Intent,
                          view: MapView) {
        context.setTheme(R.style.ATAKPluginTheme)
        super.onCreate(context, intent, view)
        pluginContext = context
        ddr = PluginTemplateDropDownReceiver(
                view, context)
        Log.d(TAG, "registering the plugin filter")
        val ddFilter = DocumentedIntentFilter()
        ddFilter.addAction(PluginTemplateDropDownReceiver.SHOW_PLUGIN)
        registerDropDownReceiver(ddr, ddFilter)

//    ddFilter    //iniitalise a cOT object
//        lateinit  var  generatedCOT: CotEvent
//
//
//        val time = CoordinatedTime()
//
//        //initialise some values to generate a COT
//        val lat = 51.0599559
//        val lon = -2.7389902
//        val hae = 0.00      //height above ellipsoid in metres
//        val ce = 2.00       //circular error radius in meteres
//        val le = 2.00       //Linear error in metres (height above target
//
//
//
//            generatedCOT.setUID("Test UID")
//            generatedCOT.setType("a-f-G-U-C-I")
//            generatedCOT.setTime(time)
//            generatedCOT.setStart(time)
//            generatedCOT.setStale(time.addMinutes(10))
//            generatedCOT.setHow("h-e")
//            generatedCOT.setPoint(CotPoint(lat,lon,hae,ce,le))
//
//
//
//            CotMapComponent.getInternalDispatcher().dispatch(generatedCOT)



    }

    override fun onDestroyImpl(context: Context, view: MapView) {
        super.onDestroyImpl(context, view)
    }

    companion object {
        private const val TAG = "PluginTemplateMapComponent"
    }
}