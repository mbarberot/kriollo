with (import <nixpkgs> {});
mkShell {
    buildInputs = [
        jdk21
        kotlin
    ];
    shellHook = ''
      export JAVA_HOME=${jdk21.home}
    '';
}
