package com.atakmap.android.plugintemplate.plugin
import com.atakmap.android.cot.CotMapComponent
import com.atakmap.coremap.cot.event.CotEvent
import com.atakmap.coremap.cot.event.CotPoint
import com.atakmap.coremap.maps.time.CoordinatedTime

class positionMarker(
           ){

    fun drawCotLocal(
                      // Constructors for the info we require when calling this function
                      lat: Double,
                      lon:Double,
                      hae: Double,
                      ce: Double,
                      le: Double,
                      uid: String,
                      type: String
               ){

        //initialise an ATAK COT object
        val  generatedCOT: CotEvent = CotEvent()

        //Generate initialise time
        val time = CoordinatedTime()

        generatedCOT.uid = uid
        generatedCOT.type = type
        generatedCOT.time = time
        generatedCOT.start = time
        generatedCOT.stale = time.addMinutes(10)
        generatedCOT.how = "h-e"
        //set the Geo Co-ords of the COT event
        generatedCOT.setPoint(CotPoint(lat,lon,hae, ce,le))

        //Write COT to map using internal dispatcher
        CotMapComponent.getInternalDispatcher().dispatch(generatedCOT)

    }

}