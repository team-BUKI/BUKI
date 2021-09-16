from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class HobbyClass(db.Model):
  __tablename__ = "hobby_class"

  id = db.Column(db.BigInteger, nullable=False, autoincrement=True, primary_key=True)
  image_url = db.Column(db.String(1000), nullable=False)
  site_url = db.Column(db.String(1000), nullable=False)
  site = db.Column(db.String(100), nullable=False)
  title = db.Column(db.String(1000), nullable=False)
  price = db.Column(db.Integer, nullable=False)
  like_cnt = db.Column(db.Integer, nullable=False)
  type = db.Column(db.String(255))
  sido_id = db.Column(db.Integer)
  sigungu_id = db.Column(db.Integer)
  big_category_id = db.Column(db.Integer)
  small_category_id = db.Column(db.Integer)

  def __repr__(self):
    return f"[HobbyClass] ({self.id}, {self.image_url}, {self.site_url}, {self.site}, {self.title}, {self.price}, {self.like_cnt})"

  def as_dict(self):
    return {x.name: getattr(self, x.name) for x in self.__table__.columns}