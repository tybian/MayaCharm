package ca.rightsomegoodgames.mayacharm.mayacomms

import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import java.nio.file.Paths

private const val mayaExecutableNameMac = "Maya"
private const val mayaExecutableNameLinux = "maya"
private const val mayaExecutableNameWin = "maya.exe"

private const val mayaPyExecutableNameMac = "mayapy"
private const val mayaPyExecutableNameLinux = "mayapy"
private const val mayaPyExecutableNameWin = "mayapy.exe"

public fun mayaPyFromMaya(path: String): String? {
    val p = Paths.get(path)

    if (p.fileName.toString() != mayaExecutableName) {
        return null
    }

    if (SystemInfo.isWindows) {
        val newPath = p.parent.resolve(mayaPyExecutableNameWin)
        return if (newPath.exists()) newPath.toString() else null
    }

    if (SystemInfo.isLinux) {
        val newPath = p.parent.resolve(mayaPyExecutableNameLinux)
        return if (newPath.exists()) newPath.toString() else null
    }

    if (SystemInfo.isMac) {
        val newPath = p.parent.parent.resolve("bin/$mayaPyExecutableNameMac")
        return if (newPath.exists()) newPath.toString() else null
    }

    return null
}

public fun mayaFromMayaPy(path: String): String? {
    val p = Paths.get(path)

    if (p.fileName.toString() != mayaPyExecutableName) {
        return null
    }

    if (SystemInfo.isWindows) {
        val newPath = p.parent.resolve(mayaExecutableNameWin)
        return if (newPath.exists()) newPath.toString() else null
    }

    if (SystemInfo.isLinux) {
        val newPath = p.parent.resolve(mayaExecutableNameLinux)
        return if (newPath.exists()) newPath.toString() else null
    }

    if (SystemInfo.isMac) {
        val newPath = p.parent.parent.resolve("MacOS/$mayaExecutableNameMac")
        return if (newPath.exists()) newPath.toString() else null
    }

    return null
}

public val mayaExecutableName: String
    get() {
        if (SystemInfo.isWindows) return mayaExecutableNameWin
        if (SystemInfo.isMac) return mayaExecutableNameMac
        if (SystemInfo.isLinux) return mayaExecutableNameLinux
        return ""
    }

public val mayaPyExecutableName: String
    get() {
        if (SystemInfo.isWindows) return mayaPyExecutableNameWin
        if (SystemInfo.isMac) return mayaPyExecutableNameMac
        if (SystemInfo.isLinux) return mayaPyExecutableNameLinux
        return ""
    }
