# Generated files index

Each time [Kriollo run its generation phase](- "#result = runGenerationPhase()"), a file called 
[codegen/generated-files.txt](- "#indexFileName") [will be generated](- "c:assert-true=isGenerated(#result, #indexFileName)").  

Note that this file, like a package-lock.json, must be added into
CVS to ensure every developer has the same Kriollo experience.