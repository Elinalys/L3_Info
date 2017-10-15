global d
global f
global date

cycle= Graph();
cycle.add_edges([[i,(i+1)%6]for i in range(6)]);

chemin=Graph();
chemin.add_edges([[i,i+1] for i in range(5)]);

cylindre = Graph.cartesian_product(cycle,chemin);

graph1 = Graph(12);
graph1.add_edges([[0,1],[1,2],[3,4],[0,5],[1,3],[2,4],[5,6],[5,8],[6,7],[7,8],[1,9],[8,9],[9,11],[9,10]]);

graph2 = DiGraph(11);
graph2.add_edges([[0,1],[2,1],[1,0],[0,2],[0,3],[3,10],[4,5],[5,4],[6,5],[6,4],[3,7],[7,8],[8,9],[9,7],[10,7],[5,10]]);

TP = Graph(14)
TP.add_edges([[0,1],[1,2],[4,3],[1,4],[2,3],[3,5],[5,6],[6,8],[7,8],[5,7],[6,9],[9,10],[10,11],[11,12],[12,9],[11,13],[13,12]])

fig1 = Graph(7) # Figure 1 of the TP document
fig1.add_edges([[0,1],[1,2],[2,0],[2,3],[3,4],[4,5],[5,6],[6,2]])

fig2 = Graph(12) # Figure 2 of the TP document
fig2.add_edges([[0,1],[1,2],[2,4],[4,3],[3,1],[4,5],[5,6],[6,8],[5,7],[7,8],[6,9],[9,10],[9,12],[10,11],[11,12],[11,13],[12,13]])

art = Graph(10) # The graph in the scientific article (given as example)
art.add_edges([[0,1],[1,2],[2,3],[0,3],[0,2],[2,4],[4,5],[5,6],[6,7],[5,7],[4,8],[8,9],[4,9],[1,4]])

no_con = Graph(5)    #no connectivity
no_con.add_edges([[0,1],[1,2],[2,0],[3,4]])

def pronfondeur_recursif(g, couleur, v, L,T,C):
    global date
    couleur[v] = 'Gris'
    date = date+1
    d[v] = date

    if v not in L:
        L.append(v)

    for voisin in g.neighbors(v):
        if couleur[voisin] == 'Noir':
            T.add_edges([[v, voisin, 1]])
            C.append([v, voisin,1])

        if couleur[voisin] == 'Blanc':
            T.add_edges([[voisin, v, 1]])
            couleur[voisin] = 'Gris'
            pronfondeur_recursif(g, couleur, voisin, L,T,C)

    couleur[v] = 'Noir'
    date = date+1
    f[v] = date
    return couleur

def decomposition_en_chaine(T,C,visited):
    print "C:" + str(C)
    for c in C:
        v = c[1]
        if v not in visited:
            if c[0] not in visited:
                visited.append([])
                visited[len(visited)-1].append(c[0])

            decomposition_en_chaine_recursif(T, visited,v)

    print "visited : " + str(visited)

def decomposition_en_chaine_recursif(T, visited, v):
    if v not in visited:
        visited[len(visited)-1].append(v)
    else:
        print "error"

    for neighbor in T.neighbors_out(v):
        if not any(neighbor in sublist for sublist in visited): # sommet non présent dans visited
            decomposition_en_chaine_recursif(T, visited, neighbor)
        else:
            return

# Retourne True s'il y a un cycle différent de C[0]
def is_there_various_circles(T,visited):
    for i in range(1, len(visited)):
        chain = visited[i]
        if chain[0] in T.neighbors_out(chain[len(chain)-1]):
            return True
    return False

def countSublists(lists):
    newList = [];
    for list in lists:
        for ele in list:
            if ele not in newList:
                newList.append(ele)

    return len(newList)

# Créé un graphe G1, qui parcourt les sommets visités dans l'ordre, et les ajoute comme cycle (éléments connexes)
def show_2_connected(G,visited):
    found = []
    G1 = Graph (G.order()+1)
    for i in visited:
        for y in range(0,len(i)):
            current = i[y]
            if i[y] in found:
                current = max(G.order(),len(found))

            if y == 0:
                G1.add_edge(i[0],i[len(i)-1])    # relie le premier element du cycle au dernier
            else:
                G1.add_edge(current,found[len(found)-1])    # relie deux sommets consecutifs du cycle ou chemin

            found.append(current)

    G1.show()    # Affichage

def max(value1, value2):
    if value1 >= value2:
        return value1
    else :
        return value2

def show_2_edge_connected(G, visited):
    found = []
    for i in visited:
        i

def Schmidt(G):
    depart = g.vertices()[0]
    couleur = []
    L = []
    C = [] # each backedge == a chain
    visited = []
    del d[:]
    del f[:]
    global date
    date = 0;
    boucle = 0;

    L.append(depart)
    T = DiGraph(len(G.vertices()));

    for v in G.vertices():
        couleur.append('Blanc')
        d.append(-1)
        f.append(-1)

    for v in G.vertices():
        if couleur[v]  == 'Blanc':
            couleur = pronfondeur_recursif(G,couleur,v,L,T,C);

        if (len(G.vertices()) != len(L)):
            print "G is not connected."
            return Graph(0)

    decomposition_en_chaine(T,C,visited)

    if len(G.vertices()) != countSublists(visited):
        print "Not a 2-EDGE-CONNECTED."
        show_2_edge_connected(G, visited)
    elif is_there_various_circles(T,visited):
        print "2-EDGE-CONNECTED BUT NOT 2-CONNECTED."
        show_2_connected(G, visited)
    else:
        print "2-CONNECTED."

    #print "parcours en profondeur : " + str(L)
    return T

g = fig1
g.show()

d = []
f = []
Schmidt(g).show()
#print "debut : " + str(d)
#print "fin : " + str(f)
