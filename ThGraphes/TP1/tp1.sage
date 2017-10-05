# TP1 sage

# Exo1

def est_eulerien(g) :
	if g.is_connected() == False :
		return False
	for i in g.degree():
		if i % 2 != 0 :
			return False
	return True

# Exo2

def parcours_en_largeur(g, s) :
	try :
		l = [] # liste des sommets déjà traités
		l.append(s)
		while g.num_verts() != len(l) :
			for vs in l :
				for v in g.neighbors(g.vertices()[vs]) :
					if v not in l :
						l.append(v)
		return l
	except IndexError, e:	
		print e

# Exo3

# complexité bof...
def parcours_en_profondeur(g, s, m=[]) : # marche si appelé avec [] en param
	m.append(s)
	for t in g.neighbors(s) :
			if t not in m :
				parcours_en_profondeur(g, t, m)
	return m

# g = Graph(5)
# g.add_edges([[0,1],[1,2],[2,3],[3,4

go = DiGraph(5)
go.add_edges([[0,1],[1,2],[2,3],[3,4]])


def tri_topologique(g) :
    l = []
    tc = []
    for i in g.vertices() :
        tc.append(0) # 0 pour blanc, 1 pour gris, 2 pour noir
    for x in g.vertices() :
        if tc[x] == 0 :
            pp_topo(g, l, x,tc)
    print(l)

def pp_topo(g, l, x, tc) :
    tc[x] = 1
    for i in g.neighbors(x) :
        if tc[i] == 0 :
            pp_topo(g, l, i,tc)
    tc[x] = 2
    l.append(x)

def cfc(g):
	l = parcours_en_profondeur(g, g.neighbors(0)[0],[])
	print l
	gt = g.reverse()
	print parcours_en_profondeur(gt, gt.neighbors(0)[0],[])
