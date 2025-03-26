emails=( "1220780@isep.ipp.pt" "1230917@isep.ipp.pt" "1230462@isep.ipp.pt" "1230948@isep.ipp.pt" "1230875@isep.ipp.pt" "1200614@isep.ipp.pt" )

names=( "Tiago Alves" "Tiago Sampaio" "Diogo Veiros" "Diogo Pereira" "Goncalo Faria" "Joao Miranda")

len=${#emails[@]}

i=0

while [ $i -lt $len ]
do
	echo -e "${names[$i]}\tcommits: `git log | grep "${emails[$i]}" | wc -l`"
	i=$(( $i + 1 ))
done



#for i in "${emails[@]}"
#do
#	echo "$i commits: `git log | grep "$i" | wc -l`"
#done


