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

def parcours_en_profondeur(g, s, m=[]) : # marche si appelé avec [] en param
	m.append(s)
	for t in g.neighbors(s) :
			if t not in m :
				parcours_en_profondeur(g, t, m)
	return m
