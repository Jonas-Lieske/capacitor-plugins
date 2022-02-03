package com.capacitorjs.plugins.googlemaps

import android.util.Log
<<<<<<< HEAD
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import com.google.android.libraries.maps.MapView
=======
import com.getcapacitor.Plugin
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.PluginMethod
import com.getcapacitor.PluginCall
>>>>>>> plugin/google-maps

@CapacitorPlugin(name = "CapacitorGoogleMaps")
class CapacitorGoogleMapsPlugin : Plugin() {
    var maps: HashMap<String, GoogleMap> = HashMap()
    val TAG: String = "CAP-GOOGLE-MAPS"

    @PluginMethod
    fun create(call: PluginCall) {
        try {
            val id = call.getString("id")

            if (null == id || id.isEmpty()) {
                handleError(call, GoogleMapErrors.INVALID_MAP_ID)
                return
            }

            val configObject = call.getObject("config")

<<<<<<< HEAD
            if (null == configObject) {
                handleError(call, GoogleMapErrors.INVALID_ARGUMENTS, "GoogleMapConfig is missing")
=======
            if(null == configObject) {
                handleError(call, GoogleMapErrors.INVALID_ARGUMENTS,
                    "GoogleMapConfig is missing")
>>>>>>> plugin/google-maps
                return
            }

            val forceCreate = call.getBoolean("forceCreate", false)!!

            val config: GoogleMapConfig

            try {
                config = GoogleMapConfig(configObject)
<<<<<<< HEAD
            } catch (e: Exception) {
                handleError(call, GoogleMapErrors.INVALID_ARGUMENTS, e.message)
                return
            }

            if (maps.contains(id)) {
                if (!forceCreate) {
=======
            }
            catch (e: Exception) {
                handleError(call, GoogleMapErrors.INVALID_ARGUMENTS, e.message)
                return;
            }

            if(maps.contains(id)) {
                if(!forceCreate) {
>>>>>>> plugin/google-maps
                    call.resolve()
                    return
                }

<<<<<<< HEAD
                destroyMapInView(id)
=======
>>>>>>> plugin/google-maps
                maps.remove(id)
            }

            val newMap = GoogleMap(config)
            maps[id] = newMap

<<<<<<< HEAD
            renderMap(newMap, id)

            call.resolve()
        } catch (e: Exception) {
=======
            call.resolve()
        }
        catch(e: Exception) {
>>>>>>> plugin/google-maps
            handleError(call, e)
        }
    }

    @PluginMethod
    fun destroy(call: PluginCall) {
        try {
            val id = call.getString("id")

            if (null == id || id.isEmpty()) {
                handleError(call, GoogleMapErrors.INVALID_MAP_ID)
                return
            }

            val removedMap = maps.remove(id)

<<<<<<< HEAD
            if (null == removedMap) {
=======
            if(null == removedMap) {
>>>>>>> plugin/google-maps
                handleError(call, GoogleMapErrors.MAP_NOT_FOUND)
                return
            }

<<<<<<< HEAD
            destroyMapInView(id)
            removedMap.mapView!!.onDestroy()

            call.resolve()
        } catch (e: Exception) {
            handleError(call, e)
        }
    }

    private fun destroyMapInView(tag: String) {
        bridge.activity.runOnUiThread {
            val viewToRemove: View? = ((bridge.webView.parent) as ViewGroup).findViewWithTag(tag)
            if (null != viewToRemove) {
                ((bridge.webView.parent) as ViewGroup).removeView(viewToRemove)
            }
        }
    }

    private fun renderMap(googleMap: GoogleMap, tag: String) {
        bridge.activity.runOnUiThread {
            val mapViewParent = FrameLayout(bridge.context)
            val layoutParams =
                    FrameLayout.LayoutParams(
                            getScaledPixels(googleMap.config.width),
                            getScaledPixels(googleMap.config.height),
                    )
            layoutParams.leftMargin = getScaledPixels(googleMap.config.x)
            layoutParams.topMargin = getScaledPixels(googleMap.config.y)

            val mapView = MapView(bridge.context, googleMap.config.googleMapOptions)

            mapViewParent.tag = tag
            mapView.layoutParams = layoutParams
            mapViewParent.addView(mapView)

            ((bridge.webView.parent) as ViewGroup).addView(mapViewParent)

            googleMap.mapView = mapView

            mapView.onCreate(null)
            mapView.onStart()
=======
            call.resolve()
        }
        catch(e: Exception) {
            handleError(call, e)
>>>>>>> plugin/google-maps
        }
    }

    private fun handleError(call: PluginCall, e: Exception) {
        val error: GoogleMapErrorObject = getErrorObject(GoogleMapErrors.UNHANDLED_ERROR, e.message)
        Log.w(TAG, error.toString())
        call.reject(e.message, error.toString(), e)
    }

    private fun handleError(call: PluginCall, errorEnum: GoogleMapErrors, message: String? = "") {
        val error: GoogleMapErrorObject = getErrorObject(errorEnum, message)
        handleError(call, error)
    }

    private fun handleError(call: PluginCall, error: GoogleMapErrorObject) {
        Log.w(TAG, error.toString())
        call.reject(error.message, error.toString())
    }
<<<<<<< HEAD

    private fun getScaledPixels(pixels: Int): Int {
        // Get the screen's density scale
        val scale = bridge.activity.resources.displayMetrics.density
        // Convert the dps to pixels, based on density scale
        return (pixels * scale + 0.5f).toInt()
    }
}
=======
}
>>>>>>> plugin/google-maps
