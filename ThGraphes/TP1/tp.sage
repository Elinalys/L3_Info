def est_eulerien(g) :
	n = 0
	nb_nodes = len(g.vertices())
	for i in g.vertices() :
		s = g.neighbors(g.vertices()[i])	
		d = g.degree(g.vertices()[s])
		if (d % 2) != 0 :
			n = n+1
		if (n == 0) | (n==2) :
			answer = True
		else :
			answer = False
	return answer