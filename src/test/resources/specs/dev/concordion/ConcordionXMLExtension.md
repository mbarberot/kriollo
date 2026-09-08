# Concordion XML Extension

An extension has been added to make it easier to use XML in Concordion tests

## With assertEquals

Concordion support multiline text and works flawlessly with most formats. 

### Plain text

As a control, we can [generate a multiline text](- "#textResult = generatePlainText()") 
and then use this syntax to verify the output using an `assertEquals` :

<pre>
<code>
&lt;pre&gt;
&lt;code concordion:assert-equals=&quot;#textResult&quot;&gt;
Line 1
Line 2
Line 3
&lt;/code&gt;
&lt;/pre&gt;
</code>
</pre>

<pre>
<code concordion:assert-equals="#textResult">
Line 1
Line 2
Line 3
</code>
</pre>
    
### XML

But, [for XML](- "#xmlResult=generateXML()"), it's a little more complicated, as the markdown is 
rendered as HTML, the XML content inside the `code` element must be
escaped : 

<pre>
<code>
&lt;pre&gt;
&lt;code concordion:assert-equals=&quot;#xmlResult&quot;&gt;
&amp;lt;lines&amp;gt;
    &amp;lt;line&amp;gt;1&amp;lt;/line&amp;gt;
    &amp;lt;line&amp;gt;2&amp;lt;/line&amp;gt;
    &amp;lt;line&amp;gt;3&amp;lt;/line&amp;gt;
&amp;lt;/lines&amp;gt;
&lt;/code&gt;
&lt;/pre&gt;
</code>
</pre>

<pre>
<code concordion:assert-equals="#xmlResult">
&lt;lines&gt;
    &lt;line&gt;1&lt;/line&gt;
    &lt;line&gt;2&lt;/line&gt;
    &lt;line&gt;3&lt;/line&gt;
&lt;/lines&gt;
</code>
</pre>

### Unescaped XML

Although the escaped version is working, it's pretty annoying to 
escape/unescape XML while writing the documentation. 

With the extension, it is now possible to write a valid XML markup
into a `code` element using the `data-type="xml"` attribute. 

When parsing the document, the extension will detect these elements
and turn escape their content automatically.

Here is the previous example, using the extension : 

<pre>
<code>
&lt;pre&gt;
&lt;code data-type=&quot;xml&quot; concordion:assert-equals=&quot;#xmlResult&quot;&gt;
&lt;lines&gt;
    &lt;line&gt;1&lt;/line&gt;
    &lt;line&gt;2&lt;/line&gt;
    &lt;line&gt;3&lt;/line&gt;
&lt;/lines&gt;
&lt;/code&gt;
&lt;/pre&gt;
</code>
</pre>

<pre>
<code data-type="xml" concordion:assert-equals="#xmlResult">
<lines>
    <line>1</line>
    <line>2</line>
    <line>3</line>
</lines>
</code>
</pre>

## With assertTrue

In that case, we will use the `set` command to save a multiline 
text, then passing it as an argument to a boolean function whose
result will go into an `assert-true` command. 

### Using plain text

Keeping the line generation algorithm, I can use this snippet : 

<pre>
<code>
&lt;pre&gt;
&lt;code concordion:set=&quot;#textInput&quot;&gt;
Line 2
Line 3
&lt;/code&gt;
&lt;/pre&gt;
</code>
</pre>

To render this code block : 

<pre>
<code concordion:set="#textInput">
Line 2
Line 3
</code>
</pre>

And check that its content [is found](- "c:assert-true=generatedTextContains(#textInput)") in : 

<pre>
<code concordion:echo="generatePlainText()"></code>
</pre>

### Escaped XML

With the same method, this snippet 

<pre>
<code>
&lt;pre&gt;
&lt;code concordion:set=&quot;#xmlEscapedResult&quot;&gt;
    &amp;lt;line&amp;gt;1&amp;lt;/line&amp;gt;
    &amp;lt;line&amp;gt;2&amp;lt;/line&amp;gt;
&lt;/code&gt;
&lt;/pre&gt;
</code>
</pre>

Will be rendered as an escaped XML input : 

<pre>
<code concordion:set="#xmlEscapedResult">
    &lt;line&gt;1&lt;/line&gt;
    &lt;line&gt;2&lt;/line&gt;
</code>
</pre>

And we can check that these lines [are found](- "c:assert-true=generatedXMLContains(#xmlEscapedResult)") in : 

<pre>
<code concordion:echo="generateXML()"></code>
</pre>

### Unescaped XML

Finally, thanks to the extension, we can use the `data-type="xml"` and unescaped XML as in the 
following snippet :  

<pre>
<code>
&lt;pre&gt;
&lt;code data-type=&quot;xml&quot; concordion:set=&quot;#xmlUnescapedResult&quot;&gt;
    &lt;line&gt;2&lt;/line&gt;
    &lt;line&gt;3&lt;/line&gt;
&lt;/code&gt;
&lt;/pre&gt;
</code>
</pre>

That will render as : 

<pre>
<code data-type="xml" concordion:set="#xmlUnescapedResult">
    <line>2</line>
    <line>3</line>
</code>
</pre>

And check that these lines [are found](- "c:assert-true=generatedXMLContains(#xmlUnescapedResult)") in :

<pre>
<code concordion:echo="generateXML()"></code>
</pre>

