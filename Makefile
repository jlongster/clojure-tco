

all:
	cd src && ~/projects/clojurescript/bin/cljsc entry.cljs '{:optimizations :advanced :target :nodejs}' > entry-output.js
