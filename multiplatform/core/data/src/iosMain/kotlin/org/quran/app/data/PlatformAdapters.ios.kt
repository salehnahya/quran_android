@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
package org.quran.app.data
import platform.Foundation.*
import platform.AVFAudio.*
import platform.darwin.NSObject
import org.quran.app.domain.*
actual fun platformSettingsStore():SettingsStore=object:SettingsStore {
 override fun get(key:String):String?=NSUserDefaults.standardUserDefaults.stringForKey(key)
 override fun set(key:String,value:String) { NSUserDefaults.standardUserDefaults.setObject(value,forKey=key) }
}
actual fun platformAudioPlayer():AudioPlayer=IosAudioPlayer()
private class IosAudioPlayer:AudioPlayer {
 private var player:AVAudioPlayer?=null
 private var completed:(()->Unit)?=null;private var failed:((String)->Unit)?=null
 override fun loadLocal(uri:String) { require(uri.startsWith("file://")) { "Only a local recording can be imported" }; val url=NSURL.URLWithString(uri) ?: error("Invalid recording URL");player?.stop();player=AVAudioPlayer(contentsOfURL=url,error=null);require(player!=null){"Recording could not be read"};player?.delegate=delegate;player?.prepareToPlay() }
 override fun play(onCompleted:()->Unit,onError:(String)->Unit) { completed=onCompleted;failed=onError;AVAudioSession.sharedInstance().setCategory(AVAudioSessionCategoryPlayback,error=null);AVAudioSession.sharedInstance().setActive(true,error=null);val audio=player;if(audio==null){onError("Import a recording first");return};if(audio.currentTime>=audio.duration)audio.currentTime=0.0;if(!audio.play())onError("Recording could not be played") }
 private val delegate = object : NSObject(), AVAudioPlayerDelegateProtocol {
 override fun audioPlayerDidFinishPlaying(player:AVAudioPlayer,successfully:Boolean) { if(successfully){player.currentTime=0.0;completed?.invoke()}else failed?.invoke("Recording could not be played") }
 }
 override fun pause() { player?.pause() }
 override fun release() { player?.stop();player?.delegate=null;player=null;completed=null;failed=null;AVAudioSession.sharedInstance().setActive(false,error=null) }
}
