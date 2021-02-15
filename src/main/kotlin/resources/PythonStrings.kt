package resources

import java.text.MessageFormat

enum class PythonStrings(val message: String) {
    UTF8_ENCODING_STR("# -*- coding: utf-8 -*-"),
    OPEN_LOG("import maya.cmds as cmds;cmds.cmdFileOutput(o=r\"{0}\")"),
    CLOSE_LOG("import maya.cmds as cmds;cmds.cmdFileOutput(closeAll=True)"),
    EXECFILE("__name__ = \"__main__\";exec(open(\"{0}\").read(), globals(), locals())"),
    PYSTDERR("# Error: "),
    PYSTDWRN("# Warning: "),
    SETTRACE("import pydevd; pydevd.settrace(host=\"{0}\", port={1,number,#}, suspend={2}, stdoutToServer={3}, stderrToServer={3})"),
    STOPTRACE("import pydevd; pydevd.stoptrace()"),
    CMDPORTSETUPSCRIPT("python/command_port_setup.py");

    fun format(vararg args: Any): String = MessageFormat.format(message, *args)

    fun getResource(vararg args: Any): String {
        val text = this::class.java.classLoader.getResource(message)?.readText() ?: return ""
        return MessageFormat.format(text, *args)
    }
}
